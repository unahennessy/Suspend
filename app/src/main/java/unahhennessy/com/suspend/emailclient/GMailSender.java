package unahhennessy.com.suspend.emailclient;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import java.net.Authenticator;


public class GMailSender  extends Authenticator
{/*
  private String mailhost = "smtp.gmail.com";
  private String password;
  private Session session;
  private String user;
  
  public GMailSender(String paramString1, String paramString2)
    throws Exception
  {
    this.user = paramString1;
    this.password = paramString2;
    Properties localProperties = new Properties();
    localProperties.setProperty("mail.transport.protocol", "smtp");
    localProperties.setProperty("mail.host", this.mailhost);
    localProperties.put("mail.smtp.auth", "true");
    localProperties.put("mail.smtp.port", "465");
    localProperties.put("mail.smtp.socketFactory.port", "465");
    localProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    localProperties.put("mail.smtp.socketFactory.fallback", "false");
    localProperties.put("mail.smtp.user", paramString1);
    localProperties.put("mail.smtp.password", paramString2);
    localProperties.setProperty("mail.smtp.quitwait", "false");
    this.session = Session.getInstance(localProperties, this);
    Transport localTransport = this.session.getTransport("smtp");
    localTransport.connect(this.mailhost, 465, paramString1, paramString2);
    localTransport.close();
  }
  
  protected PasswordAuthentication getPasswordAuthentication()
  {
    return new PasswordAuthentication(this.user, this.password);
  }
  
  public void sendMail(String paramString1, String paramString2, String paramString3, String paramString4)
    throws Exception
  {
    for (;;)
    {
      try
      {
        MimeMessage localMimeMessage = new MimeMessage(this.session);
        if (paramString2 != null)
        {
          int i = paramString2.trim().length();
          if (i != 0) {}
        }
        else
        {
          return;
        }
        DataHandler localDataHandler = new DataHandler(new ByteArrayDataSource(paramString2.getBytes(), "text/plain"));
        localMimeMessage.setSender(new InternetAddress(paramString3));
        localMimeMessage.setSubject(paramString1);
        localMimeMessage.setDataHandler(localDataHandler);
        if (paramString4.indexOf(',') > 0)
        {
          localMimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(paramString4));
          try
          {
            Transport.send(localMimeMessage);
            AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + "Send Mail", "Info");
            AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Sender: " + paramString3, "Info");
            AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Recipient: " + paramString4, "Info");
            AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Body: " + paramString2, "Info");
            AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Mail Sent Successfully.", "Info");
          }
          catch (Exception localException)
          {
            AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + "Send Mail", "Info");
            AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Sender: " + paramString3, "Info");
            AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Recipient: " + paramString4, "Info");
            AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Body: " + paramString2, "Info");
            AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Error: " + localException.getMessage(), "Error");
          }
        }
        else
        {
          localMimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(paramString4));
        }
      }
      finally {}
    }
  }
  
  public class ByteArrayDataSource
    implements DataSource
  {
    private byte[] data;
    private String type;
    
    public ByteArrayDataSource(byte[] paramArrayOfByte)
    {
      this.data = paramArrayOfByte;
    }
    
    public ByteArrayDataSource(byte[] paramArrayOfByte, String paramString)
    {
      this.data = paramArrayOfByte;
      this.type = paramString;
    }
    
    public String getContentType()
    {
      if (this.type == null) {
        return "application/octet-stream";
      }
      return this.type;
    }
    
    public InputStream getInputStream()
      throws IOException
    {
      return new ByteArrayInputStream(this.data);
    }
    
    public String getName()
    {
      return "ByteArrayDataSource";
    }
    
    public OutputStream getOutputStream()
      throws IOException
    {
      throw new IOException("Not Supported");
    }
    
    public void setType(String paramString)
    {
      this.type = paramString;
    }
  }*/
}//END OF GMAILSENDER.JAVA