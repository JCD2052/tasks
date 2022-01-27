package email;

import config.ConfigReader;
import model.User;
import utils.EmailUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

public class EmailService {
    private static final String WORD_FOR_SEARCHING = "Downloads";

    public List<String> getTextBodiesFromEmail(User user, BoxName boxName) {
        List<String> messages = new ArrayList<>();
        try (Store store = SessionFactory.getInstance()
                .getSession().getStore(ConfigReader.getProperty("mailStoreType"))) {
            store.connect(ConfigReader.getProperty("mail.imap.host"), user.getEmail(), user.getPassword());
            Folder inbox = store.getFolder(boxName.getBoxName());
            inbox.open(Folder.READ_ONLY);
            messages = Arrays
                    .stream(inbox.getMessages())
                    .sorted(sortByDate())
                    .map(this::getBodyText)
                    .collect(Collectors.toList());
            inbox.close();
            return messages;
        } catch (MessagingException e) {
            return messages;
        }
    }

    private String getBodyText(Message message) {
        try {
            return EmailUtils.getTextFromMessage(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    private Comparator<Message> sortByDate() {
        return (message1, message2) -> {
            try {
                return message2.getSentDate().compareTo(message1.getSentDate());
            } catch (MessagingException e) {
                e.printStackTrace();
                return -1;
            }
        };
    }

    public static boolean checkEmailForContent(List<String> emails, User user) {
        boolean found = false;
        for (String bodyText : emails) {
            bodyText = bodyText.toLowerCase();
            if (bodyText.contains(user.getProductName().toLowerCase())
                    && bodyText.contains(user.getOs().toLowerCase())
                    && bodyText.contains(WORD_FOR_SEARCHING.toLowerCase())) {
                found = true;
                break;
            }
        }
        return found;
    }
}