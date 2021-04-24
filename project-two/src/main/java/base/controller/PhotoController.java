package base.controller;

import base.dao.PhotoDao;
import base.dao.PhotoDaoImpl;
import base.model.Photos;
import base.service.PhotoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PhotoController {

    final static Logger socialLog = Logger.getLogger(PhotoController.class);
    private PhotoDaoImpl photoDao;
    private PhotoService photoService;


    /**
     * http://localhost:9005/social/api/uploadPhoto
     * HTTP request to upload a photo to s3 storage and then database
     * @param file
     * @param name
     * @param modelMap
     * @return
     */
    @PostMapping(value="/uploadPhoto")
    @CrossOrigin(allowCredentials = "true")
    public Photos uploadPhoto(@RequestParam("imageData") MultipartFile file,
                              @RequestParam("photoString") String name, ModelMap modelMap){
        socialLog.info("In uploadPhoto method");

        modelMap.addAttribute("imageData", file);

        //get extension
        String orgFileName = file.getOriginalFilename();
        int dotIndex = orgFileName.lastIndexOf('.');
        String imgExt = orgFileName.substring(dotIndex);
        //check for file type here?
        //name += imgExt;

        Photos photo = new Photos();

        File store = new File("src/main/resources/tmpfiles/"+name+".tmp");

        try {
            file.transferTo(store);
            socialLog.info("tempfile for "+orgFileName+" stored with new name: "+ name);
        }catch (Exception e){
            socialLog.error("Failed to transfer file "+orgFileName, e);
        }
        photo.setImageData(store);

        photo.setPhotoString(name);

        //print out new photoname to check if valid
        photoService.uploadPhoto(photo);

        //clear out photo data and delete store prior to return
        photo.clearData();
        if(store.delete()) socialLog.info("tempfile for "+name+" deleted");

        //return so front end can handle
        return photo;
    }


    ////Constructors

    public PhotoController(){
        socialLog.info("In no arg constructor for PhotoController");
    }

    ///Autowired constructor to inject the repo directly

    @Autowired
    public PhotoController(PhotoDaoImpl photoDao, PhotoService photoService){
        socialLog.info("In constructor for PhotoController with reqs photoDao and photoService");
        this.photoDao = photoDao;
        this.photoService = photoService;
    }


    ////Getters and Setters

    public PhotoDao getUserDao() {
        socialLog.info("In getter for photoDao");
        return photoDao;
    }

    @Autowired
    public void setPhotoDao(PhotoDaoImpl photoDao) {
        socialLog.info("In setter for photoDao");
        this.photoDao = photoDao;
    }

    public PhotoService getPhotoService() {
        socialLog.info("In getter for photoService");
        return photoService;
    }

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        socialLog.info("In setter for photoService");
        this.photoService = photoService;
    }
}
