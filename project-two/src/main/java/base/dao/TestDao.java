package base.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDao {

    public static String url = "jdbc:h2:C:\\Users\\chake\\Desktop\\h2Test";
    public static String username = "sa";
    public static String password = "sa";

    public static void h2InitDao () {
        try(Connection conn = DriverManager.getConnection(url,username,password)) {
            String sql= "CREATE TABLE users (\n" +
                    "\tuser_id SERIAL PRIMARY KEY\n" +
                    "\t,\tuser_fname varchar(100)\n" +
                    "\t,\tuser_lname varchar(100)\n" +
                    "\t,\tuser_email varchar(150) UNIQUE \n" +
                    "\t,\tuser_password varchar(150)\n" +
                    "\t,\tuser_username varchar(150) UNIQUE \n" +
                    "\t, \tuser_avatar varchar(100)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE user_post (\n" +
                    "\tuser_id int\n" +
                    "\t,\tpost_id SERIAL PRIMARY KEY\n" +
                    "\t,\tpost_description varchar(255)\n" +
                    "\t, \tCONSTRAINT user_post_id FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE  \n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE likes (\n" +
                    "\tlike_id SERIAL PRIMARY KEY\n" +
                    "\t,\tuser_id int\n" +
                    "\t,\tpost_id int\n" +
                    "\t, \tCONSTRAINT like_user_id FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE  \n" +
                    "\t,\tCONSTRAINT like_post_id FOREIGN KEY (post_id) REFERENCES user_post(post_id) ON DELETE CASCADE  \n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE photos (\n" +
                    "\tphoto_id SERIAL PRIMARY KEY\n" +
                    "\t,\tpost_id int\n" +
                    "\t,\tphotobyte varchar(150)\n" +
                    "\t,\tCONSTRAINT photo_post_id FOREIGN KEY (post_id) REFERENCES user_post(post_id) ON DELETE CASCADE\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "INSERT INTO users (user_fname, user_lname, user_email, user_password, user_username, user_avatar) \n" +
                    "VALUES ('Frank', 'LeHioya', 'revaturefrank@gmail.com', '123', 'Mikey', 'Life is like an apple. You never know when one might drop on you');    \n" +
                    "\n" +
                    "INSERT INTO users (user_fname, user_lname, user_email, user_password, user_username, user_avatar) \n" +
                    "VALUES ('Lia', 'Summer', 'summer@email.com', '123', 'Ms.Summer', 'Sunshine and Rainbows');    \n" +
                    "\n" +
                    "INSERT INTO users (user_fname, user_lname, user_email, user_password, user_username, user_avatar) \n" +
                    "VALUES ('John', 'Big', 'Big@email.com', '123', 'Destroyer', 'EVIL');    \n" +
                    "\n" +
                    "\n" +
                    "INSERT INTO user_post (post_description, user_id)\n" +
                    "VALUES ('post', 1);\n" +
                    "\n" +
                    "INSERT INTO user_post (post_description, user_id)\n" +
                    "VALUES ('post 2', 1);\n" +
                    "\n" +
                    "INSERT INTO user_post (post_description, user_id)\n" +
                    "VALUES ('post 3', 1);";

            Statement state = conn.createStatement();
            state.execute(sql);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public static void h2DestroyDao() {
        try(Connection conn=
                    DriverManager.getConnection(url,username, password))
        {
            String sql= "" +
                    "DROP TABLE photos;\n" +
                    "DROP TABLE likes;\n" +
                    "DROP TABLE user_post;\n" +
                    "DROP TABLE users;";

            Statement state = conn.createStatement();
            state.execute(sql);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
