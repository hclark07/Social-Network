//package base.dao;
//
//import base.model.User;
//import org.hibernate.SessionFactory;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@ContextConfiguration(locations = "applicationContextTest.xml", classes = UserDaoImpl.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//class UserDaoImplTest {
//
//    @Autowired
//    private SessionFactory sesFact;
//
//    @Autowired
//    UserDaoImpl userDao;
//
//    @BeforeEach
//    void setUp() {
//        TestDao.h2InitDao();
//        userDao.setSesFact(sesFact);
//
//    }
//
//    @AfterEach
//    void tearDown() { TestDao.h2DestroyDao();
//    }
//
//    @Test
//    @Transactional
//    @Rollback(true)
//    void createUser() {
//        User user1 = new User("Bell", "LeHioya", "revaturefrank@gmail.com", "123456", "Belly", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_1.jpg", "Life is like an apple. You never know when one might drop on you");
//        userDao.createUser(user1);
//        User user2 = userDao.getUserByFullName("Bell", "LeHioya");
//        assertEquals(user2.toString(), user1.toString());
//
//    }
//
//    @Test
//    void updateUser() {
//        User user1 = new User(1,"Frank", "LeHioya", "revaturefrank@gmail.com", "123", "Mikey", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_1.jpg", true, "Life is like an apple. You never know when one might drop on you");
//        assertTrue(userDao.updateUser(user1));
//
//    }
//
//    @Test
//    void getAllUsersLoggedIn() {
//        User user1 = new User(1,"Frank", "LeHioya", "revaturefrank@gmail.com", "123", "Mikey", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_1.jpg", true, "Life is like an apple. You never know when one might drop on you");
//        List<User> Loggedin = new ArrayList<>();
//        userDao.login(user1.getEmail(),user1.getPassword());
//        Loggedin = userDao.getAllUsersLoggedIn();
//        assertEquals(user1.toString(),Loggedin.get(0).toString());
//    }
//
//    @Test
//    void getAllUsers() {
//        List<User> allUsers = userDao.getAllUsers();
//        User dan = new User("Frank", "LeHioya", "revaturefrank@gmail.com", "123", "Mikey", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_1.jpg", "Life is like an apple. You never know when one might drop on you");
//        User dan2 = new User("Lia", "Summer", "summer@email.com", "123", "Ms.Summer", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_2", "You can’t blame gravity for falling in love");
//        User dan3 = new User("John", "Big", "Big@email.com", "123", "Destroyer", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_3", "My owner's shoes looked so lovely together. It was a pity I destroyed one of them.");
//
//        assertAll(
//                ()->assertEquals(allUsers.get(0).getEmail(), dan.getEmail()),
//                ()->assertEquals(allUsers.get(1).getEmail(), dan2.getEmail()),
//                ()->assertEquals(allUsers.get(2).getEmail(), dan3.getEmail())
//        );
//
//    }
//
//
//    @Test
//    void login() {
//        User user1 = new User(1,"Frank", "LeHioya", "revaturefrank@gmail.com", "123", "Mikey", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_1.jpg", true, "Life is like an apple. You never know when one might drop on you");
//        User user2 = userDao.login(user1.getEmail(),user1.getPassword());
//        assertEquals(user1.toString(),user2.toString());
//    }
//
//
//    @Test
//    void getUserByEmail() {
//        User user1 = new User(1,"Frank", "LeHioya", "revaturefrank@gmail.com", "123", "Mikey", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_1.jpg", "Life is like an apple. You never know when one might drop on you");
//        String email = "revaturefrank@gmail.com";
//        assertEquals(user1.toString(),userDao.getUserByEmail(email).toString());;
//    }
//
//    @Test
//    void getUserByFullName() {
//        User user1 = new User(1,"Frank", "LeHioya", "revaturefrank@gmail.com", "123", "Mikey", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_1.jpg", "Life is like an apple. You never know when one might drop on you");
//        assertEquals(user1.toString(),userDao.getUserByFullName("Frank","LeHioya").toString());
//
//    }
//}