package base.dao;

import base.controller.UserController;
import base.model.Likes;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import javax.transaction.Transactional;

@Repository("likesDao")
@Transactional
public class LikesDaoImpl implements LikesDao {

    final static Logger socialLog = Logger.getLogger(LikesDaoImpl.class);
    SessionFactory sesFact;

    /**
     * Takes a user_id and post_id and adds to a postLikes junction table
     * @param like
     */
    public void addLike(Likes like){
        sesFact.getCurrentSession().persist(like);
        socialLog.info("Added like from "+like.getUserId()+" to db");
    }

    /**
     * Takes a user_id and post_id and removes entity from postLikes junction table
     * @param like
     */
    @Override
    public void unLike(Likes like) {

        int postId = like.getPostId();
        int userId = like.getUserId();
        Query query = sesFact.getCurrentSession().createQuery("DELETE from Likes WHERE post_id ='" + postId + "' AND user_id = '" + userId +"'");

        query.executeUpdate();
        socialLog.info("Removed like from "+userId+" from db");
    }

    ////Constructors

    public LikesDaoImpl(){
        socialLog.info("LikesDaoImpl no arg constructor called");
    }

    @Autowired
    public LikesDaoImpl(SessionFactory sesFact) {
        socialLog.info("LikesDaoImpl constructor with sesFact req called");
        this.sesFact = sesFact;
    }

    ////Getters and Setters


    public SessionFactory getSesFact() {
        socialLog.info("sesFact requested via getter");
        return sesFact;
    }

    @Autowired
    public void setSesFact(SessionFactory sesFact) {
        socialLog.info("sesFact set called via setter");
        this.sesFact = sesFact;
    }
}
