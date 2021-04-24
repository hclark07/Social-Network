package base.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="User_Post")

public class Post {

    //Auto generated serial number and primary key of User_Post
    @Id
    @Column(name="post_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    //Column post description
    @Column(name = "post_description")
    private String description;

    //Link to media that can be displayed in post
    @Column(name = "media")
    private String media;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_submitted")
    private Date submitted;

    //Each post can have many photos
    //Photos are stored in the photoList List
    @OneToMany(mappedBy = "myPost", fetch = FetchType.EAGER)
    private List<Photos> photoList = new ArrayList<>();

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User userId;

    @ManyToMany
    @JoinTable(
            name="PostLikes",
            joinColumns = {@JoinColumn (name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users = new ArrayList<>();


    //Constructors
    public Post() {
    }

    public Post(String description, User userId) {
        this.description = description;
        this.userId = userId;
    }

    public Post(String description, List<Photos> photoList, User userId) {
        this.description = description;
        this.photoList = photoList;
        this.userId = userId;
    }

    public Post(int postId, String description, User userId) {
        this.postId = postId;
        this.description = description;
        this.userId = userId;
    }

    public Post(int postId, String description, User userId, List<User> users) {
        this.postId = postId;
        this.description = description;
        this.userId = userId;
        this.users = users;
    }

    public Post(int postId, String description, Date submitted, List<Photos> photoList, User userId) {
        this.postId = postId;
        this.description = description;
        this.submitted = submitted;
        this.photoList = photoList;
        this.userId = userId;
    }

    public Post(int postId, String description, Date submitted, List<Photos> photoList, User userId, List<User> users) {
        this.postId = postId;
        this.description = description;
        this.submitted = submitted;
        this.photoList = photoList;
        this.userId = userId;
        this.users = users;
    }

    //Getters and Setters
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    //toString() method

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", description='" + description + '\'' +
                ", media='" + media + '\'' +
                ", submitted=" + submitted +
                ", photoList=" + photoList +
                ", userId=" + userId +
                ", users=" + users +
                '}';
    }
}
