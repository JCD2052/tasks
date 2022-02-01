package pages.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import org.openqa.selenium.By;
import pages.BasePage;
import utils.StringUtils;

public class Wall extends BasePage {
    private final static String POST_CONTENT_LOCATOR = "//div[contains(@class, 'post') and contains(@class, 'info')]";
    private final static String REPLIES_CONTENT_LOCATOR = "//div[@class = 'replies']";
    private final static String POST_IMAGE_LOCATOR = POST_CONTENT_LOCATOR + "//div[contains(@class, 'thumb')]//a";
    private final static String POST_MESSAGE_LOCATOR = POST_CONTENT_LOCATOR + "//child::div[contains(@class, 'text')]";

    public Wall() {
        super(By.xpath("//div[@id='profile_wall']"), "Wall");
    }

    public int getUserID() {
        return StringUtils.stringToIntWithRemoving(AqualityServices
                .getBrowser()
                .getCurrentUrl());
    }

    private String setPostLocator(int postID) {
        return String.format(
                "//div[contains(@id, '%s') and contains(@id, 'post') and contains(@id, '%s')]", getUserID(), postID);
    }

    public ContentInfo getPostMessageInfo(int postID) {
        String postMessage = getElementFactory()
                .getLabel(By.xpath(setPostLocator(postID) + POST_MESSAGE_LOCATOR),
                        "Message Text").getText();
        String postAuthor = getElementFactory()
                .getLabel(By.xpath(setPostLocator(postID) + "//h5[contains(@class, 'author')]//a"),
                        "Author Name").getAttribute("href");
        return new ContentInfo(postAuthor, postMessage);
    }

    public void clickLikePost(int postID) {
        getElementFactory()
                .getButton(By.xpath(setPostLocator(postID) +
                                "//following-sibling::div[contains(@class, 'btns')]/div[not(@title)]"),
                        "Like Post").click();
    }

    private ILink getImage(int postID) {
        return getElementFactory()
                .getLink(By.xpath(setPostLocator(postID) + POST_IMAGE_LOCATOR),
                        "Post Image.");
    }

    public boolean isImageExist(int postID) {
        return getImage(postID).state().waitForDisplayed();
    }

    public String getImageLink(int postID) {
        return getImage(postID).getHref();
    }

    private ILabel getPost(int postID) {
        return getElementFactory()
                .getLabel(By.xpath(setPostLocator(postID)), "Post");
    }

    public boolean isPostNotExistOnPage(int postID) {
        return getPost(postID)
                .state()
                .waitForNotDisplayed();
    }

    public boolean isPostExistOnPage(int postID) {
        return getPost(postID)
                .state()
                .waitForDisplayed();
    }

    public void clickShowNextComment(int postID) {
        String nextCommentsLocator = setPostLocator(postID) +
                REPLIES_CONTENT_LOCATOR + "//span[contains(@class, 'next') and contains(@class, 'label')]";
        getElementFactory()
                .getButton(By.xpath(nextCommentsLocator), "Next Comments")
                .click();
    }

    public ContentInfo getPostCommentInfo(int commentID, int postID) {
        String commentLocator = String.format(setPostLocator(postID) +
                        REPLIES_CONTENT_LOCATOR +
                        "//div[contains(@data-post-id, '%s') and contains(@data-post-id,  '%s')]",
                getUserID(), commentID);
        String commentAuthor = getElementFactory()
                .getLabel(By.xpath(commentLocator + "//div[contains(@class, 'author')]//a"),
                        "Comment Author")
                .getAttribute("data-from-id");
        String commentMessage = getElementFactory()
                .getLabel(By.xpath(commentLocator + "//div[contains(@class, 'text') and contains(@class, 'wall')]"),
                        "Comment Message")
                .getText();
        return new ContentInfo(commentAuthor, commentMessage);
    }

    public static class ContentInfo {
        private final String authorID;
        private final String message;

        public ContentInfo(String authorID, String message) {
            this.authorID = authorID;
            this.message = message;
        }

        public int getAuthorID() {
            return StringUtils.stringToIntWithRemoving(authorID);
        }

        public String getMessage() {
            return message;
        }
    }
}