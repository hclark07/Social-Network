package base.controller;

import base.model.User;
import base.dao.UserDaoImpl;
import base.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
public class SessionController {

    final static Logger socialLog = Logger.getLogger(SessionController.class);
    private UserDaoImpl userDao;
    private UserService uServ;



    /**
     * http://localhost:9005/social/api/getUser/
     * HTTP request for current user in session
     * Grabs a current user session and returns the user object as json to the front end
     * @param session
     * @return
     */
    @GetMapping(value="/api/getUser")
    @CrossOrigin(allowCredentials = "true")
    public User getCurrentUser(HttpSession session) {
        socialLog.info("In getCurrentUser method");
        User user = (User) session.getAttribute("currentUser");
        return user;
    }


    /**
     * http://localhost:9005/social/login
     * HTTP request to check in an user email and encrypted user password
     * If successful returns a user from the database
     * If user not null sets a session with key 'currentUser' and changes logged in status to true
     * @param user
     * @param session
     * @return
     */
    @PostMapping(value="/login")
    @CrossOrigin(allowCredentials = "true")
    public @ResponseBody
    User login(@RequestBody User user, HttpSession session){
        socialLog.info("In login method");
        String email = user.getEmail();
        String password = uServ.encryptPass(user.getPassword());
        User loggedInUser = userDao.login(email, password);
        if(loggedInUser != null){
            socialLog.info("Found matching user for "+email);
            loggedInUser.setLoginStatus(true);
            session.setAttribute("currentUser", loggedInUser);
            userDao.updateUser(loggedInUser);
            loggedInUser.setPassword(user.getPassword());
            return loggedInUser;
        }
        socialLog.info("No matching credentials for "+email);
        return null;
    }

    /**
     * http://localhost:9005/social/logout
     * HTTP request that sets user's loginStatus to false, updates the postgres table with loginstatus,
     * then invalidates the HttpSession.
     * @param user
     * @param req
     * @return
     */

    @GetMapping(value="/logout")
    public void logout(@RequestBody User user, HttpServletRequest req){
        socialLog.info("Logging out user ID"+user.getUserId());
        user.setLoginStatus(false);
        userDao.updateUser(user);
        HttpSession session = req.getSession();
        session.invalidate();
        socialLog.info("Logout complete");
    }

    public SessionController(){
        socialLog.info("In no arg constructor for SessionController");
    }

    @Autowired
    public SessionController(UserDaoImpl userDao, UserService uServ) {
        socialLog.info("In constructor for SessionController with reqs userDao and uServ");
        this.userDao = userDao;
        this.uServ = uServ;
    }

    public UserDaoImpl getUserDao() {
        socialLog.info("Request for userDao via getter");
        return userDao;
    }

    public UserService getuServ() {
        socialLog.info("Request for uServ via getter");
        return uServ;
    }

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        socialLog.info("Setting userDao via setter");
        this.userDao = userDao;
    }

    public void setuServ(UserService uServ) {
        socialLog.info("Setting uServ via setter");
        this.uServ=uServ;
    }

}
