package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import testdata.TestDataReader;
import utils.StringUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageInfo implements Comparable<MessageInfo> {
    private String bodyContent;
    private String subjectInfo;
    private Date receivedDate;


    @Override
    public int compareTo(MessageInfo o) {
        return o.getReceivedDate().compareTo(this.getReceivedDate());
    }

    public boolean containsContentInSubject(String contentToSearch) {
        return this.subjectInfo.contains(contentToSearch);
    }

    public boolean containsProductInfo(UserProductInfo userProductInfo) {
        String url = StringUtils.extractUrl(this.bodyContent);
        String content = StringUtils.substringTextBeforeValue(this.bodyContent, url);
        System.out.println(content);
        return (content.contains(userProductInfo.getProductName().toLowerCase())
                && content.contains(userProductInfo.getOs().toLowerCase())
                && (content.contains(TestDataReader.getProperty("WORD_TO_FIND_FOR_DESKTOP").toLowerCase())
                || content.contains(TestDataReader.getProperty("WORD_TO_FIND_FOR_MOBILE").toLowerCase())));
    }
}