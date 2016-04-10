package proyecto_uoct.common.util;

import javax.mail.PasswordAuthentication;

/**
* SimpleAuthenticator is used to do simple authentication when the SMTP
* server requires it.
*/
public class SMTPAuthenticator extends javax.mail.Authenticator {
    private String u;
    private String p;

    public SMTPAuthenticator(String user,String psw){
        u=user;
        p=psw;

    }

    public void setDatos(String user,String psw){
        u=user;
        p=psw;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(u,p);
    }
}

