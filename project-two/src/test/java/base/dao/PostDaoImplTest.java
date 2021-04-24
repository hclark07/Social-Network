//package base.dao;
//
//import base.model.Post;
//import base.model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PostDaoImplTest {
//
//    private PostDao postDao;
//    private User user1;
//    private List<Post> posts;
//
//    @BeforeEach
//    public void setup () {
//        //Mock results from a database
//        user1 = new User(1,"Frank", "LeHioya", "revaturefrank@gmail.com", "123", "Mikey", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_1.jpg", true, "Life is like an apple. You never know when one might drop on you");
//        Post post = new Post("post", user1);
//        Post post2 = new Post("post 2", user1);
//        Post post3 = new Post("post 3", user1);
//        posts.add(post);
//        posts.add(post2);
//        posts.add(post3);
//    }
//
//    @Test
//    void createPost() {
//        Post post4 = new Post("post 4", user1);
//        posts.add(postDao.createPost(post4));
//
//        assertEquals(post4.toString(), posts.get(3).toString());
//    }
//
//    @Test
//    void getAllPosts() {
//        List<Post> allPost = postDao.getAllPosts();
//        assertAll(
//                ()->assertEquals(posts.get(0).toString(),allPost.get(0).toString()),
//                ()->assertEquals(posts.get(1).toString(),allPost.get(1).toString()),
//                ()->assertEquals(posts.get(2).toString(),allPost.get(2).toString())
//        );
//    }
//}