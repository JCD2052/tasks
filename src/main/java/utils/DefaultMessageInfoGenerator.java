package utils;

import model.MessageInfo;

public class DefaultMessageInfoGenerator {
    public static MessageInfo getDefaultMessageInfo() {
        return MessageInfo.builder()
                .bodyContent("No body")
                .receivedDate(DateUtils.getRandomDate())
                .subjectInfo("No subject")
                .build();
    }
}
