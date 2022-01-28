package email;

import config.ConfigReader;
import testdata.TestDataReader;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class StoreFactory {
    private static StoreFactory storeInstance;
    private static Store store;

    private StoreFactory() {
    }

    static StoreFactory getInstance() {
        if (storeInstance == null) {
            storeInstance = new StoreFactory();
        }
        return storeInstance;
    }

    Store getStore() throws MessagingException {
        if (store == null) {
            store = SessionFactory.getInstance()
                    .getSession()
                    .getStore(ConfigReader.getProperty("mailStoreType"));
        }
        connectStore();
        return store;
    }

    private void connectStore() {
        try {
            store.connect(ConfigReader.getProperty("mail.imap.host"),
                    TestDataReader.getProperty("EMAIL_ADDRESS"),
                    TestDataReader.getProperty("EMAIL_PASSWORD"));
        } catch (MessagingException e) {
            e.printStackTrace();
            store = null;
        }
    }


    private static class SessionFactory {
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
}
