package base.dao;

import base.model.User;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {


    final static Logger socialLog = Logger.getLogger(UserDaoImpl.class);
    private SessionFactory sesFact;

    /**
     * Inserts new user into the "user" table
     * @param user
     */
    @Override
    public void createUser(User user) {
        sesFact.getCurrentSession().save(user);
        socialLog.info("Created a new user in the db");
    }

    /**
     * Update existing user information
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        String id = String.valueOf(user.getUserId());
        sesFact.getCurrentSession().saveOrUpdate(id, user);
        socialLog.info("Updated user ID"+id+" in the db");
        return true;
    }


    /**
     * Returns a list of all users loggedin
     * @return
     */
    @Override
    public List<User> getAllUsersLoggedIn() {

        List loggedIn = sesFact.getCurrentSession().createQuery("from User WHERE loggedIn = '" + true + "' ").list();
        socialLog.info("Got all logged in users from db");

        return loggedIn;
    }

    /**
     * Returns a list of all users in the database
     * @return
     */
    public List<User> getAllUsers() {
        List<User> friendList = sesFact.getCurrentSession().createQuery("from User", User.class).list();
        socialLog.info("Got all users from db");
//        for(User u: friendList)
        return friendList;
    }

    /**
     * Utilizes an HQL query to return a single unique result from the user table.
     * Returns user with corresponding email and password authorization.
     * @param email
     * @param password
     * @return
     */
    @Override
    public User login(String email, String password) {
        User user = sesFact.getCurrentSession().createQuery("from User WHERE email = '" + email + "' AND password = '" + password +"'", User.class).getSingleResult();
        socialLog.info("Got user from db");
        return user;
    }

    /**
     * Returns a user based on their unique email
     * @param user_email
     * @return
     */
    @Override
    public User getUserByEmail(String user_email) {
        User user = sesFact.getCurrentSession().createQuery("from User WHERE email = '" + user_email + "'", User.class).getSingleResult();
        socialLog.info("Got user from db via email");
        return user;
    }

    @Override
    public User getUserByFullName(String firstName, String lastName) {
        User user = sesFact.getCurrentSession().createQuery("from User WHERE user_fname = '" + firstName + "' AND user_lname = '" + lastName +"'", User.class).uniqueResult();
        return user;
    }



    ////Constructors

    public UserDaoImpl(){
        socialLog.info("In no arg constructor for UserDaoImpl");
    }

    public UserDaoImpl(SessionFactory sesFact) {
        socialLog.info("In no arg constructor for UserDaoImpl");
        this.sesFact = sesFact;
    }

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
