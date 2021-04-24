package util;

import base.controller.LikesController;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    final static Logger socialLog = Logger.getLogger(LikesController.class);
    private static SessionFactory sf= new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private static Session session = null;


    public static Session getSession(){

        //open a session if it doesn't exist yet
        socialLog.info("Getting a session");
        if(session == null){
            socialLog.info("Creating a new session");
            session = sf.openSession();
        }
        return session;
    }

    public static void closeSession(){
        socialLog.info("Closing all sessions");
        session.close();
        session = null;
        sf.close();
    }
}
