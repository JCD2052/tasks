package email;

import model.MessageInfo;
import model.UserProductInfo;
import org.testng.Assert;
import testdata.TestDataReader;
import utils.DefaultMessageInfoGenerator;
import utils.EmailUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

public class EmailService {

    public List<MessageInfo> getTextBodiesFromEmail(BoxName boxName) {
        try (Store store = StoreFactory.getInstance().getStore();
             Folder emailFolder = store.getFolder(boxName.getBoxName())) {
            emailFolder.open(Folder.READ_ONLY);
            return Arrays
                    .stream(emailFolder.getMessages())
                    .map(this::getMessageInfo)
                    .filter(messageInfo ->
                            messageInfo.containsContentInSubject(TestDataReader.getProperty("SUBJECT_BASE_WORD")))
                    .sorted()
                    .collect(Collectors.toList());
        } catch (MessagingException e) {
            Assert.fail("No Emails", e);
            return new ArrayList<>();
        }
    }

    private MessageInfo getMessageInfo(Message message) {
        try {
            return new MessageInfo(EmailUtils.getTextFromMessage(message),
                    message.getSubject(),
                    message.getReceivedDate());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return DefaultMessageInfoGenerator.getDefaultMessageInfo();
    }

    public static boolean checkEmailsForContent(List<MessageInfo> emails, UserProductInfo userProductInfo) {
        return emails
                .stream()
                .anyMatch(messageInfo -> messageInfo.containsProductInfo(userProductInfo));
    }
}