package utils;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;

import email.ContentType;
import org.jsoup.Jsoup;

public class EmailUtils {
    public static String getTextFromMessage(Message message) {
        try {
            MimeMultipart mimeMultipartMessage = (MimeMultipart) message.getContent();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < mimeMultipartMessage.getCount(); i++) {
                BodyPart bodyPart = mimeMultipartMessage.getBodyPart(i);
                if (bodyPart.isMimeType(ContentType.HTML_CONTENT_TYPE)) {
                    result.append(Jsoup.parse((String) bodyPart.getContent()).text());
                } else {
                    result.append(bodyPart.getContent());
                }
            }
            return result
                    .toString()
                    .toLowerCase();
        } catch (MessagingException | IOException exception) {
            exception.printStackTrace();
            return "No info";
        }
    }
}