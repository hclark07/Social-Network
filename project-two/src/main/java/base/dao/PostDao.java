package base.dao;

import base.model.Post;

import java.util.List;

public interface PostDao {

    Post createPost(Post post);

    List<Post> getAllPosts();

}
