package unahhennessy.com.suspend.emailclient;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */

import android.util.Log;

import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class GMailSender extends javax.mail.Authenticator {
    private String mMailHost = "smtp.gmail.com";
    private String mUser;
    private String mPassword;
    private Session mSession;
    private static final String TAG = "GMailSender";
    static {
        Security.addProvider(new unahhennessy.com.suspend.emailclient.JSSEProvider());
    }

    public GMailSender(String mUser, String mPassword) {
        this.log("entered GMailSender() within GMailSender.java");
        this.mUser = mUser;
        this.mPassword = mPassword;

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", mMailHost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        mSession = Session.getDefaultInstance(props, this);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        this.log("entered getPasswordAuthentication() within GMailSender.java");
        return new PasswordAuthentication(mUser, mPassword);
    }

    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception
    {   this.log("entered sendMail() within GMailSender.java");
        try{
            MimeMessage mMimeMessage = new MimeMessage(mSession);
            DataHandler mDataHandler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
            mMimeMessage.setSender(new InternetAddress(sender));
            mMimeMessage.setSubject(subject);
            mMimeMessage.setDataHandler(mDataHandler);
            if (recipients.indexOf(',') > 0)
                mMimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            else
                mMimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
            Transport.send(mMimeMessage);
        }catch(Exception e)
        {
             e.printStackTrace();
        }
    }

    
    private void log(String msg)
    {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, msg);

    }
}