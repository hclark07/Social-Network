package base.dao;

import com.amazonaws.services.s3.AmazonS3;
import base.model.Photos;

public interface PhotoDao {

    public void uploadPhoto(Photos photo, AmazonS3 client);

    public Photos getPhotobyId(int id, AmazonS3 client);

    public void deletePhoto(Photos photo, AmazonS3 client);

    public void uploadAvatarPhoto (int id, String amazonPhotoUrl);
}
