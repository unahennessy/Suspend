package unahhennessy.com.suspend.emailclient;
/**
 * Created by unahe_000 on 06/06/2015 unahhennessy.com.suspend.emailclient Suspend.
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;


 public class ByteArrayDataSource implements DataSource
 {
     // used by GMailSender emailclient
        private byte[] mData;
        private String mType;

        public ByteArrayDataSource(byte[] mData, String mType)
        {
            super();
            this.mData = mData;
            this.mType = mType;
        }


        public void setType(String mType) {
            this.mType = mType;
        }

        public String getContentType()
        {
            if (mType == null)
                return "application/octet-stream";
            else
                return mType;
        }

        public InputStream getInputStream() throws IOException
        {
            return new ByteArrayInputStream(mData);
        }

        public String getName()
        {
            return "ByteArrayDataSource";
        }

        public OutputStream getOutputStream() throws IOException
        {
            throw new IOException("Not Supported");
        }
 }

