import api.models.likes.ResponseGetLikes;
import api.service.LikesService;
import api.service.PhotoService;
import api.service.WallService;
import config.ConfigReader;
import models.WallPostInfo;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.FeedPage;
import pages.LogIn;
import pages.MyProfile;
import pages.forms.LeftMenuItems;
import pages.forms.Wall;
import testdata.TestDataReader;
import utils.ImageUtils;
import utils.StringUtils;
import utils.WebDriverUtils;

import java.util.List;

public class VKTest extends BaseTest {

    @Test
    public void testCase1() {
        Wall wallForm = myProfilePage.goToWall();
        int userID = wallForm.getUserID();
        String randomText = StringUtils.getRandomString(StringUtils
                .stringToInt(TestDataReader.get("random_string_length")));
        WallService wallService = new WallService();
        int postID = wallService.createPost(randomText, "").getPostID();
        Assert.assertTrue(wallForm.isPostExistOnPage(postID),
                "No Post");

        Wall.ContentInfo commentInfo = wallForm.getPostMessageInfo(postID);
        Assert.assertEquals(commentInfo.getMessage(), randomText,
                "Message doesn't match");

        Assert.assertEquals(commentInfo.getAuthorID(), userID,
                "Author doesn't match");

        String testImagePath = TestDataReader.get("test_image_path");
        String photoAttachment = new PhotoService()
                .getUploadedPhotoAsAttachment(testImagePath);
        randomText = StringUtils.getRandomString(StringUtils
                .stringToInt(TestDataReader.get("random_string_length")));
        Assert.assertEquals(
                wallService.editPost(postID, randomText, photoAttachment), 1);

        Assert.assertTrue(wallForm.isImageExist(postID),
                "No image for post");

        new SoftAssert().assertTrue(
                ImageUtils.checkImages(
                        wallForm.getImageLink(postID), testImagePath),
                "Images are not equal");

        Assert.assertEquals(
                wallForm.getPostMessageInfo(postID).getMessage(), randomText,
                "Message doesn't match");

        randomText = StringUtils.getRandomString(StringUtils
                .stringToInt(TestDataReader.get("random_string_length")));
        int commentID = wallService.createCommentToPost(postID, randomText, "");
        wallForm.clickShowNextComment(postID);
        commentInfo = wallForm.getPostCommentInfo(commentID, postID);
        Assert.assertEquals(commentInfo.getMessage(), randomText,
                "Message doesn't match");

        Assert.assertEquals(commentInfo.getAuthorID(), userID,
                "Author doesn't match");

        wallForm.clickLikePost(postID);
        ResponseGetLikes likesInfo = new LikesService().getPostLikes(postID);
        Assert.assertTrue(likesInfo.getItems().contains(userID),
                "AuthorId is not in likes list");

        Assert.assertEquals(likesInfo.getCount(), 1,
                "Like counter doesn't match");

        Assert.assertEquals(wallService.deletePost(postID), 1,
                "Post hasn't been deleted");

        Assert.assertTrue(wallForm.isPostNotExistOnPage(postID),
                "Post is still exist");
    }

    @Test
    public void testCase2() {
        String user1PageUrl = WebDriverUtils.getCurrentUrl();
        Wall wallForm = myProfilePage.goToWall();
        int userId = wallForm.getUserID();
        String randomText = StringUtils.getRandomString(StringUtils
                .stringToInt(TestDataReader.get("random_string_length")));
        WallService wallService = new WallService();
        int postID = wallService.createPost(randomText, "").getPostID();
        WallPostInfo postInfo = WallPostInfo.builder()
                .fromID(userId)
                .ownerID(userId)
                .postID(postID)
                .text(randomText)
                .build();
        Assert.assertTrue(wallForm.isPostExistOnPage(postID),
                "No Post");

        Assert.assertTrue(wallService.getPosts().getItems().contains(postInfo),
                "No info about post");

        wallForm.clickLikePost(postID);
        myProfilePage.goToHeader().goToProfile().logOut();
        WebDriverUtils.goToPage(ConfigReader.get("base_url"));

        LogIn logInPage = new LogIn();
        Assert.assertTrue(logInPage.isPageLoaded(),
                "Login Page is not opened");

        logInPage.logIn(TestDataReader.get("username_user2"),
                TestDataReader.get("password_user2"));
        FeedPage feedPage = new FeedPage();
        Assert.assertTrue(feedPage.isPageLoaded(),
                "Feed Page is not opened.");

        feedPage.goToLeftMenu().selectMenuItem(LeftMenuItems.MY_PROFILE);
        myProfilePage = new MyProfile();

        Assert.assertTrue(myProfilePage.isPageLoaded(),
                "My Page is not opened.");
        wallForm = myProfilePage.goToWall();
        int user2Id = wallForm.getUserID();
        WebDriverUtils.goToPage(user1PageUrl);
        myProfilePage = new MyProfile();
        Assert.assertTrue(myProfilePage.isPageLoaded(),
                "My Page is not opened.");

        wallForm = myProfilePage.goToWall();
        Wall.ContentInfo commentInfo = wallForm.getPostMessageInfo(postID);
        Assert.assertEquals(commentInfo.getMessage(), randomText,
                "Message doesn't match");

        Assert.assertEquals(commentInfo.getAuthorID(), userId,
                "Author doesn't match");

        wallForm.clickLikePost(postID);
        List<Integer> likes = new LikesService().getPostLikes(postID).getItems();
        System.out.println(likes);

        Assert.assertTrue(likes.contains(userId),
                "No likes from User1");

        Assert.assertTrue(likes.contains(user2Id),
                "No likes from User2");
    }

    @Test
    public void testCase3() {
        String user1PageUrl = WebDriverUtils.getCurrentUrl();
        Wall wallForm = myProfilePage.goToWall();
        int userId = wallForm.getUserID();
        String randomText = StringUtils.getRandomString(StringUtils
                .stringToInt(TestDataReader.get("random_string_length")));
        WallService wallService = new WallService();
        int postID = wallService.createPost(randomText, "").getPostID();
        WallPostInfo postInfo = WallPostInfo.builder()
                .fromID(userId)
                .ownerID(userId)
                .postID(postID)
                .text(randomText)
                .build();
        Assert.assertTrue(wallForm.isPostExistOnPage(postID),
                "No Post");

        Assert.assertTrue(wallService.getPosts().getItems().contains(postInfo),
                "No info about post");

        myProfilePage.goToHeader().goToProfile().logOut();
        WebDriverUtils.goToPage(ConfigReader.get("base_url"));
        WebDriverUtils.goToPage(user1PageUrl);
        myProfilePage = new MyProfile();

        Assert.assertTrue(myProfilePage.isPageLoaded(),
                "My Page is not opened.");

        wallForm = myProfilePage.goToWall();
        Wall.ContentInfo commentInfo = wallForm.getPostMessageInfo(postID);
        Assert.assertEquals(commentInfo.getMessage(), randomText,
                "Message doesn't match");

        Assert.assertEquals(commentInfo.getAuthorID(), userId,
                "Author doesn't match");

        Assert.assertFalse(myProfilePage.goToLeftMenu().isLogIn(),
                "User is still logged in.");
    }
}