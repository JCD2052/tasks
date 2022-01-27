import email.BoxName;
import email.EmailService;
import model.User;

import java.util.List;

public class DemoEmail {
    public static void main(String[] args) {
        String username = "vovajcd252@gmail.com";// change accordingly
        String password = "Zcd123456";// change accordingly
        User user = User.builder()
                .email(username)
                .password(password)
                .productName("Total Security")
                .os("Windows")
                .build();
        List<String> bodies = new EmailService().getTextBodiesFromEmail(user, BoxName.INBOX);
        System.out.println(bodies);
        System.out.println(EmailService.checkEmailForContent(bodies, user));
    }
}
