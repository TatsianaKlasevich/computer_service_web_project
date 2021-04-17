package com.klasevich.cms.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * @author Tatsiana Klasevich
 * The Session factory for sending emails
 */
public class SessionFactory {
    /**
     * Create session session.
     *
     * @param configProperties the config properties for sending email
     * @return the session for sending email
     */
    public static Session createSession(Properties configProperties) {
        String userName = configProperties.getProperty("mail.user.name");
        String userPassword = configProperties.getProperty("mail.user.password");
        return Session.getDefaultInstance(configProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}
