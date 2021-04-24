package base.controller;


import base.dao.UserDao;
import base.dao.UserDaoImpl;

import base.model.User;
import base.service.UserService;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    final static Logger socialLog = Logger.getLogger(UserController.class);
    private UserDaoImpl userDao;
    private UserService uServ;


    /**
     * http://localhost:9005/social/api/getAllFriends
     * HTTP request for a list of All users in the database
     * Returns a list of all users in the database
     * @return
     */
    @GetMapping(value = "/getAllFriends")
    @CrossOrigin(allowCredentials = "true")

    public @ResponseBody List<User> getAllFriends () {
        socialLog.info("In getAllFriends call");
        List<User> friendList = userDao.getAllUsers();
        return friendList;
    }

    /**
     * http://localhost:9005/social/api/createUser
     * HTTP request to add a new user to the database
     * Takes inputted password and encrypts it before adding user
     * @param user
     */
    @PostMapping(value="/createUser" , produces="application/json")
    @CrossOrigin(allowCredentials = "true")
    public void createNewUser(@RequestBody User user){
        socialLog.info("In createNewUser call");
        String encryptPass = uServ.encryptPass(user.getPassword());
        user.setPassword(encryptPass);
        userDao.createUser(user);
    }


    /**
     * http://localhost:9005/social/api/updateUser
     * HTTP request to update existing user information
     * Takes password and encrypts before inputting data
     * @param newUser
     */
    @PostMapping(value="/updateUser")
    @CrossOrigin(allowCredentials = "true")
    public void updateUser(@RequestBody User newUser, HttpSession session){
        socialLog.info("In updateUser call");
        String encrpytPass = uServ.encryptPass(newUser.getPassword());
        newUser.setPassword(encrpytPass);
        userDao.updateUser(newUser);
        session.setAttribute("currentUser",newUser);
    }

    /**
     * http://localhost:9005/social/api/emailPassword
     * HTTP request that sends an email to is given in the email param
     * If the user exists in the database send an email with decrypted password
     * @param email
     */
    @GetMapping(value="/emailPassword", params = {"email"})
    public void emailPassword (String email) {
        socialLog.info("In emailPassword call");
        User user = userDao.getUserByEmail(email);
        String decrpytPass = uServ.decryptPass(user.getPassword());
        user.setPassword(decrpytPass);
        uServ.passwordReset(user);
    }


    ////Constructors

    public UserController(){
        socialLog.info("In no arg constructor for UserController");
    }

    ///Autowired constructor to inject the repo directly

    @Autowired
    public UserController(UserDaoImpl userDao, UserService uServ){
        socialLog.info("In Usercontroller constructor with UserDao/UserService reqs");
        this.userDao = userDao;
        this.uServ = uServ;
    }

    ////Getters and Setters

    public UserDao getUserDao() {
        socialLog.info("In getter for userDao");
        return userDao;
    }

    public UserService getuServ() {
        socialLog.info("In getter for uServ");
        return uServ;
    }


    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        socialLog.info("In setter for userDao");
        this.userDao = userDao;
    }

    public void setuServ(UserService uServ) {
        socialLog.info("In setter for uServ");
        this.uServ=uServ;
    }


}
