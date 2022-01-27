package email;

import config.ConfigReader;

import javax.mail.Session;

public class SessionFactory {
    private static SessionFactory sessionInstance;
    private static Session session;

    private SessionFactory() {
    }

    static SessionFactory getInstance() {
        if (sessionInstance == null) {
            sessionInstance = new SessionFactory();
        }
        return sessionInstance;
    }

    Session getSession() {
        if (session == null) {
            session = Session.getDefaultInstance(ConfigReader.getPropertiesContent());
        }
        return session;
    }
}