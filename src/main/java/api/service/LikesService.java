package api.service;

import api.models.likes.RequestGetLikes;
import api.models.likes.ResponseGetLikes;
import api.routes.APIMethods;
import api.routes.Endpoints;

public class LikesService extends BaseService {

    public ResponseGetLikes getPostLikes(int postId) {
        RequestGetLikes likesRequest = RequestGetLikes.builder()
                .postID(postId)
                .type("post")
                .filter("likes")
                .build();
        return getBaseRequest(APIMethods.GET_LIKES, likesRequest, ResponseGetLikes.class);
    }

    @Override
    protected Endpoints getBasePath() {
        return Endpoints.LIKES;
    }
}
