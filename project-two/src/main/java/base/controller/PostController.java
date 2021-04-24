package base.controller;

import base.dao.PostDaoImpl;
import base.dao.UserDaoImpl;
import base.model.Post;
import base.model.User;
import base.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class    PostController {

    final static Logger socialLog = Logger.getLogger(PostController.class);
    private PostDaoImpl postDao;
    private UserDaoImpl userDao;


    /**
     * http://localhost:9005/social/api/getAllPosts
     * HTTP request for a list of all posts
     * @return
     */
    @GetMapping(value="/getAllPosts")
    public @ResponseBody
    List<Post> getAllPosts(){
        socialLog.info("In getAllPosts method");
        List<Post> postList = postDao.getAllPosts();
        return postList;
    }


    /**
     * http://localhost:9005/social/api/post/create
     * Create a new post and persist in database
     * then return the new post with post_id for s3 storage to save a image file
     * @param newPost
     */
    @PostMapping(value="/post/create")
    @CrossOrigin(allowCredentials = "true")
    public @ResponseBody
    Post createNewPost(@RequestBody Post newPost){
        socialLog.info("In createNewPost method");
        newPost = postDao.createPost(newPost);
        return newPost;
    }

    /**
     * Exception handler for HTTP 400
     * sout exception that occurs
     * @param e
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        socialLog.error("Returning HTTP 400 bad request: ", e);
    }


    ////Constructors

    public PostController(){
        socialLog.info("In no args constructor for PostController");
    }

    @Autowired
    public PostController(PostDaoImpl postDao) {
        socialLog.info("In postDao constructor for PostController");
        this.postDao = postDao;
        insertInitialValues();
    }


    ////Getters and Setters

    public PostDaoImpl getPostDao() {
        socialLog.info("In getter for PostControllerDao");
        return postDao;
    }

    public void setPostDao(PostDaoImpl postDao) {
        socialLog.info("In setter for PostControllerDao");
        this.postDao = postDao;
    }

    /**
     * Our initializer to intialize our database
     */
    public void insertInitialValues(){
        UserService uServ = new UserService();

        socialLog.info("Setting initial values for application");
        String pass1 = uServ.encryptPass("12356");

        User dan = new User("Frank", "LeHioya", "revaturefrank@gmail.com", pass1, "Mikey", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_1.jpg", "Life is like an apple. You never know when one might drop on you");
        User dan2 = new User("Lia", "Summer", "summer@email.com", pass1, "Ms.Summer", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_2", "You can’t blame gravity for falling in love");
        User dan3 = new User("John", "Big", "Big@email.com", pass1, "Destroyer", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_3", "My owner's shoes looked so lovely together. It was a pity I destroyed one of them.");


        Post post1 = new Post(1, "post", dan);
        Post post2 = new Post(1, "post here too", dan2);
        Post post3 = new Post(1,"post here too", dan3);

        postDao.createPost(post1);
        postDao.createPost(post2);
        postDao.createPost(post3);


    }
}
