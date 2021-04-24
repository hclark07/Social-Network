package base.service;

import base.dao.UserDaoImpl;
import base.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Properties;


@Service("uServ")
@Transactional
public class UserService {

    private UserDaoImpl userDao;
    final static Logger socialLog = Logger.getLogger(UserService.class);
    private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";
    private static final String SALT = "saltykeypattern";



    ////Constructors

    public UserService(){

    }

    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }


    ////Getters and Setters for UserDaoImpl userDao

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }


    ////Business logic

    /**
     * createUser(User user) takes one User object as a parameter and inserts it into
     * the database. It does not return the new user object, as all relevant data is already
     * available to the front-end.
     * @param user
     */
    public void createUser(User user){
        userDao.createUser(user);
    }

    public void updateUser(User user){

        ///updateUser returns a boolean, insert logic here to work with that
        userDao.updateUser(user);
    }

    /**
     * Returns a List of all User objects currently logged into the web app
     * @return List<User>
     */

    public List<User> getAllLoggedInUsers(){
        return userDao.getAllUsersLoggedIn();
    }

    public User login(String email, String password){
        return userDao.login(email, password);
    }

//    public User getUserByFullName(String firstName, String lastName){
//        return userDao.getUserByFullName(firstName, lastName);
//    }

    /**
     * Takes in a password and encrypts it so that the password can be safely stored  in the database
     * Takes in a string password and returns an encrypted string.
     * @param pass
     * @return
     */
    public String encryptPass(String pass){
        socialLog.info("In encryptPass method");
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(pass.getBytes(StandardCharsets.UTF_8)));
        }catch (Exception e){
            socialLog.error("FAILED TO ENCRYPT", e);
            return null;
        }
    }

    /**
     * Takes in a encrypted string and decryptes into a string password
     * @param pass
     * @return
     */
    public String decryptPass(String pass) {
        socialLog.info("In decryptPass method");
        String decryptedText=null;
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(pass)));

        } catch (Exception e) {
            socialLog.error("FAILED TO DECRYPT PASSWORD", e);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Create an session with google messaging servers to send an email message
     * if user exists in database
     * @param user
     */
    public static void passwordReset (User user) {
        socialLog.info("In passwordReset method for user "+user.getEmail());
        // email ID of Recipient.

        // email ID/password of  Sender.
        String myAccountEmail = "revaturepractice2@gmail.com";
        String password = "p@ssw0rd$";



        // Getting system properties
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Setting up mail server
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");

        properties.setProperty("mail.smtp.port", "587");




        // creating session object to get properties
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);

            }
        });

        prepareMessage(session, myAccountEmail, user);




    }

    /**
     * Prepares message
     * Sends a email with head forgot something? and a decrypted password if user exists in system
     * @param session
     * @param myAccountEmail
     * @param user
     */
    public static void prepareMessage (Session session, String myAccountEmail, User user) {
        try
        {
            socialLog.info("Preparing message for email password recovery");

            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress(myAccountEmail));

            // Set To Field: adding recipient's email to from field.
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));

            // Set Subject: subject of the email
            message.setSubject("Forgot something?");

            // set body of the email.
            message.setText("Your last recorded password:\n" +
                    user.getPassword());

            Transport.send(message);
            socialLog.info("Mail successfully sent!");
        }
        catch (MessagingException mex) {
            socialLog.error("FAILED TO SEND MESSAGE", mex);
            mex.printStackTrace();
        }
    }


}
