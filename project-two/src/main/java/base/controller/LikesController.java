package base.controller;

import base.dao.LikesDaoImpl;
import base.model.Likes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class LikesController {

    private LikesDaoImpl likesDao;
    final static Logger socialLog = Logger.getLogger(LikesController.class);


    /**
     * http://localhost:9005/social/api/likePost
     * HTTP request to add a user_id and post_id to a postLikes junction table
     * @param like
     */
    @PostMapping(value="/likePost")
    public void likePost(@RequestBody Likes like){
        socialLog.info("Calling likePost functionality");
        likesDao.addLike(like);
    }


    /**
     * http://localhost:9005/social/api/unlikePost
     * HTTP request to remove a user_id and post_id from a postLikes junction table
     * @param like
     */
    @PutMapping(value="/unlikePost")
    public void unlikePost(@RequestBody Likes like){
        socialLog.info("Calling unlikePost functionality");
        likesDao.unLike(like);
    }



    ////Constructors

    public LikesController(){
        socialLog.info("likes controller no args constructor has been referenced");
    }

    @Autowired
    public LikesController(LikesDaoImpl likesDao) {

        this.likesDao = likesDao;
        socialLog.info("likes controller likesDao constructor has been referenced");
    }



    ////Getters and Setters

    public LikesDaoImpl getLikesDao() {
        socialLog.info("Getting likesDao from getter");
        return likesDao;
    }

    @Autowired
    public void setLikesDao(LikesDaoImpl likesDao) {
        socialLog.info("Setting likesDao in setter");
        this.likesDao = likesDao;
    }


}

