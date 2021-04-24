package base.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import base.dao.PhotoDaoImpl;
import base.model.Photos;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Map;

@Service("photoService")
@Transactional
public class PhotoService {

    final static Logger socialLog = Logger.getLogger(PhotoService.class);
    PhotoDaoImpl photoDao;

    public static Map<String, String> env = System.getenv();
    public static String ACCESS_KEY_ID = "AKIAQG2OO4PTDQ27M2Z6";
    public static String ACCESS_SEC_KEY = "+SWSR917+tKkMZBAhqzP/DgHDJTkRc+tBC2Ira9K";


    ////Constructors

    public PhotoService(){
        socialLog.info("In no arg constructor for PhotoService");
    }

    public PhotoService(PhotoDaoImpl photoDao) {
        socialLog.info("In constructor for PhotoService with photoDao req");
        this.photoDao = photoDao;
    }


    ////Getters and Setters

    public PhotoDaoImpl getPhotoDao() {
        return photoDao;
    }

    public void setPhotoDao(PhotoDaoImpl photoDao) {
        this.photoDao = photoDao;
    }


    ////Business Logic
    //dao calls

    /**
     * Upload photos
     * @param photo
     */
    public void uploadPhoto(Photos photo){
        socialLog.info("Calling uploadPhoto service");
        //for some reason this isn't injected.. have to look into it later
        photoDao = new PhotoDaoImpl();
        photoDao.uploadPhoto(photo, getS3Client());
    }

    public Photos getPhotobyId(int id){
        return photoDao.getPhotobyId(id, getS3Client());
    }

    public void deletePhoto(Photos photo){
        photoDao.deletePhoto(photo, getS3Client());
    }


    //misc logic

    /**
     * Using key_id and sec_key grabs s3client session
     * @return
     */
    public AmazonS3 getS3Client(){
        socialLog.info("Creating S3 client");

        //create a credentials object to identify server for authentification
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY_ID, ACCESS_SEC_KEY);

        //create client connection based on credentials
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();

        return s3client;
    }
}
