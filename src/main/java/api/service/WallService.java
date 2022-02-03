package api.service;

import api.models.wall.RequestCreateComment;
import api.models.wall.RequestDeletePost;
import api.models.wall.RequestEditPost;
import api.models.wall.RequestWallPost;
import api.models.wall.ResponseCreateComment;
import api.models.wall.ResponseGetPosts;
import api.models.wall.ResponseWallPost;
import api.routes.APIMethods;

public class WallService extends BaseService {

    public ResponseWallPost createPost(String message, String attachments) {
        RequestWallPost request = RequestWallPost
                .builder()
                .message(message)
                .attachments(attachments)
                .build();
        return getBaseRequest(APIMethods.WALL_POST, request, ResponseWallPost.class);
    }

    public int createCommentToPost(final int postID, String message, String attachments) {
        RequestCreateComment replyRequest = RequestCreateComment.builder()
                .postID(postID)
                .message(message)
                .attachments(attachments)
                .build();
        return getBaseRequest(APIMethods.WALL_CREATE_REPLY, replyRequest, ResponseCreateComment.class)
                .getCommentID();
    }

    public int editPost(int postID, String message, String attachments) {
        RequestEditPost requestEditPost = RequestEditPost.builder()
                .postID(postID)
                .message(message)
                .attachments(attachments)
                .build();
        return getBaseRequest(APIMethods.WALL_EDIT, requestEditPost, Integer.class);
    }
    public ResponseGetPosts getPosts(){
        return getBaseRequest(APIMethods.WALL_GET, null, ResponseGetPosts.class);
    }

    public int deletePost(int postID) {
        RequestDeletePost deletePostRequest = RequestDeletePost.builder()
                .postID(postID)
                .build();
        return getBaseRequest(APIMethods.WALL_DELETE, deletePostRequest, Integer.class);
    }
}
