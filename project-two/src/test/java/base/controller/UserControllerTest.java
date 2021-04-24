//package base.controller;
//
//import base.dao.UserDaoImpl;
//import base.model.User;
//import base.service.UserService;
//import com.amazonaws.services.auditmanager.model.ListAssessmentsRequest;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//class UserControllerTest {
//
//    @Mock
//    private UserDaoImpl userDao;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void getAllFriends() throws Exception{
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());
//
//        when(userController.getAllFriends()).thenReturn((List) users);
//
//        mockMvc.perform(get("/api/getAllFriends"))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("users", hasSize(2)));
//
//    }
//
//    @Test
//    void createNewUser() throws Exception {
////        User user1 = new User("Bell", "Clark", "bell@email.com","123", "Belly");
////
////        doNothing().when(userController.createNewUser(isA(User.class));
////
////        mockMvc.perform(post("/api/createUser"))
////                .andExpect(status().isOk())
////                .andExpect()
//    }
////
////    @Test
////    void updateUser() {
////    }
//
////    @Test
////    void emailPassword() {
////
////    }
//}