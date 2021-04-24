package base.dao;

import base.model.User;

import java.util.List;

public interface UserDao {

         void createUser(User user);

         User login(String email, String password);

         User getUserByEmail(String email);

         boolean updateUser(User user);

         List<User> getAllUsersLoggedIn();

         List<User> getAllUsers();

         User getUserByFullName(String firstName, String lastName);
}
