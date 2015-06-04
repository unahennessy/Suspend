package unahhennessy.com.suspend.emailclient;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.os.Message;
import android.service.textservice.SpellCheckerService;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.security.Security;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

public class GMailSender extends Authenticator
{
  private String mailhost = "smtp.gmail.com";
  private String user;
  private char[] password;
  private SpellCheckerService.Session session;

      static
      {
      Security.addProvider(new com.provider.JSSEProvider());
      }
    public GMailSender(String user, char[] password)
    {
        this.user = user;
        this.password = password;

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");


          session = new SpellCheckerService.Session()
          {
            @Override
            public void onCreate()
            {

            }

            @Override
            public SuggestionsInfo onGetSuggestions(TextInfo textInfo, int suggestionsLimit)
            {
              return null;
            }
          };
    }

  protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(user, password);
  }

  public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception
  {
    try{
      MimeMessage message = new MimeMessage(session);
      DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
      message.setSender(new InternetAddress(sender));
      message.setSubject(subject);
      message.setDataHandler(handler);
      if (recipients.indexOf(',') > 0)
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
      else
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
      Transport.send(message);
    }catch(Exception e){

    }
  }

  public class ByteArrayDataSource implements DataSource
  {
    private byte[] data;
    private String type;

    public ByteArrayDataSource(byte[] data, String type) {
      super();
      this.data = data;
      this.type = type;
    }

    public ByteArrayDataSource(byte[] data) {
      super();
      this.data = data;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getContentType() {
      if (type == null)
        return "application/octet-stream";
      else
        return type;
    }

    public InputStream getInputStream() throws IOException {
      return new ByteArrayInputStream(data);
    }

    public String getName() {
      return "ByteArrayDataSource";
    }

    public OutputStream getOutputStream() throws IOException {
      throw new IOException("Not Supported");
    }

      @Override
      public Connection getConnection() throws SQLException {
          return null;
      }

      @Override
      public Connection getConnection(String theUsername, String thePassword) throws SQLException {
          return null;
      }

      @Override
      public int getLoginTimeout() throws SQLException {
          return 0;
      }

      @Override
      public PrintWriter getLogWriter() throws SQLException {
          return null;
      }

      @Override
      public void setLoginTimeout(int seconds) throws SQLException {

      }

      @Override
      public void setLogWriter(PrintWriter out) throws SQLException {

      }

      @Override
      public <T> T unwrap(Class<T> iface) throws SQLException {
          return null;
      }

      @Override
      public boolean isWrapperFor(Class<?> iface) throws SQLException {
          return false;
      }
  }
}