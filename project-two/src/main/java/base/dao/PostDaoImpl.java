package base.dao;

import base.model.Post;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("postDao")
@Transactional
public class PostDaoImpl implements PostDao {

    final static Logger socialLog = Logger.getLogger(PostDaoImpl.class);

    private SessionFactory sesFact;


    ////Business Logic

    /**
     * add a new post and then return that post for image saving
     * @param post
     * @return
     */
    @Override

    public Post createPost(Post post) {
        sesFact.getCurrentSession().save(post);
        socialLog.info("Saved post to db");
        return post;
    }



    /**
     * return an array of all posts in the database in descending order based on time submitted
     * @return
     */
    public List<Post> getAllPosts(){
        List<Post> userPosts = sesFact.getCurrentSession().createQuery("FROM Post ORDER BY post_submitted DESC", Post.class).list();
        for(Post p : userPosts){
            Hibernate.initialize(p.getUsers());

        }
        socialLog.info("Got all posts in db");
        return userPosts;
    }

    ////Constructors

    public PostDaoImpl(){
        socialLog.info("Called no arg constructor for PostDaoImpl");
    }

    public PostDaoImpl(SessionFactory sesFact) {
        socialLog.info("Called constructor for PostDaoImpl with sesFact");
        this.sesFact = sesFact;
    }



    ////Getters and Setters

    public SessionFactory getSesFact() {
        socialLog.info("Getter called for sesFact");
        return sesFact;
    }

    @Autowired
    public void setSesFact(SessionFactory sesFact) {
        socialLog.info("Setter called for sesFact");
        this.sesFact = sesFact;
    }
}
