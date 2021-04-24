package base.service;

import base.dao.PostDaoImpl;
import base.model.Post;


public class PostService {

    PostDaoImpl postDao;


    ////Constructors

    public PostService(){

    }

    public PostService(PostDaoImpl postDao) {
        this.postDao = postDao;
    }


    ////Getters and Setters

    public PostDaoImpl getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDaoImpl postDao) {
        this.postDao = postDao;
    }


    ////Business Logic

    public void createPost(Post post){
        postDao.createPost(post);
    }



}
