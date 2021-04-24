package base.model;


import javax.persistence.*;
import java.io.File;

//Photo model
@Entity
@Table(name = "Photos")
public class Photos {

    //Auto generated serial number and primary key of Hibernate_Photos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private int photoId;

    //Column photo string
    @Column(name = "photobyte")
    private String photoString;

    //Many photos can be attached to one post
    //Connected to Post in the @JoinColumn portion with a new column named post_id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="post_id")
    private Post myPost;

    //Stores file data
    private File imageData;


    public Photos() {
    }

    public Photos(int photoId) {
        this.photoId = photoId;
    }

    public Photos(int photoId, String photoString, Post myPost) {
        this.photoId = photoId;
        this.photoString = photoString;
        this.myPost = myPost;
    }

    public Photos(String photoString, Post myPost) {
        this.photoString = photoString;
        this.myPost = myPost;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPhotoString() {
        return photoString;
    }

    public void setPhotoString(String photoString) {
        this.photoString = photoString;
    }

    public Post getMyPost() {
        return myPost;
    }

    public void setMyPost(Post myPost) {
        this.myPost = myPost;
    }

    public File getImageData() {
        return imageData;
    }

    public void setImageData(File imageData){
        this.imageData = imageData;
    }


    public void clearData(){
        this.imageData = null;
    }

    @Override
    public String toString() {
        System.out.println("In to string");
        return "Photos{" +
                "photoId=" + photoId +
                ", photoString='" + photoString + '\'' +
//                ", myPost=" + myPost +
                '}';
    }
}
