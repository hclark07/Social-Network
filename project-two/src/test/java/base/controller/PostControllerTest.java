//package base.controller;
//
//import base.dao.PostDaoImpl;
//import base.model.Post;
//import base.model.User;
//import com.amazonaws.services.mq.model.BadRequestException;
//import org.hamcrest.Matcher;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.isA;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//class PostControllerTest {
//
//    @Mock
//    private PostDaoImpl postDao;
//
//    @InjectMocks
//    private PostController postController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
//    }
//
//    @AfterEach
//    void tearDown(){
//
//    }
//
//    @Test
//    void getAllPosts() throws Exception{
//        List<Post> posts = new ArrayList<>();
//        posts.add(new Post());
//        posts.add(new Post());
//
//        when(postController.getAllPosts()).thenReturn((List) posts);
//
//        mockMvc.perform(get("/api/getAllFriends"))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("users", hasSize(2)));
//    }
//
//    @Test
//    void createNewPost() throws Exception {
//
//        Post newPost = new Post("Hello", new User());
//
//        when(postController.createNewPost(newPost)).thenReturn(newPost);
//
//        mockMvc.perform(post("/api/post/create"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.description", Matcher.is("Hello")));
//    }
//
//    @Test
//    void handle() throws Exception {
//        Exception e;
//
//        doThrow(HttpClientErrorException.BadRequest)
//                .when(postController.handle(any(Exception.class)));
//                .andExpect(status().isBadRequest());
//
//
//    }
//}