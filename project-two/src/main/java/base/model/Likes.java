package base.model;

import javax.persistence.*;


@Entity
@Table(name = "PostLikes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private int likeId;


    @Column(name="user_id")
    private int userId;


    @Column(name="post_id")
    private int postId;

    public Likes(){

    }

    /**
     * Likes(userId, likeId) constructor
     *
     * @param userId
     * @param postId
     */
    public Likes(int postId, int userId){
        this.userId = userId;
        this.postId = postId;
    }

    public Likes(int likeId, int postId, int userId){
        this.likeId = likeId;
        this.userId = userId;
        this.postId = postId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
