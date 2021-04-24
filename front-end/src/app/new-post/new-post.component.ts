import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from '../models/post';
import { User } from '../models/user';
import{Photo} from '../models/photo';
import { PostService } from '../services/post.service';
import { UserServicesService } from '../services/user-services.service';
import { PhotoServicesService } from '../services/photo-services.service';
import { Subscription } from 'rxjs';
import { PostsComponent } from '../posts/posts.component';


@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  userSubscription: Subscription;
  postSubscription: Subscription;
  post: Post = {
    postId: 0,
    description: '',
    photos: [],
    media: '',
    userId: null,
    users: []
  }

  photo: Photo = {
    photoId: 0,
    photoString: '',
    // post: null,
    imageData: null
  };

  user: User;

  selectedFile: File;


  constructor(private postService: PostService, private userService: UserServicesService, 
    private photoService: PhotoServicesService, private router: Router) { }


  ngOnInit(): void {
    this.currentUser();
  }

  ngOnDestroy(){
    this.userSubscription.unsubscribe();
  }

  get descriptionField(){
    return this.post.description;
  }

  set descriptionField(str: string){
    this.post.description = str;
  }

  currentUser() {
    //subscribes to user returned from user service
   this.userSubscription = this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
      }
    )
  }

  //Should store the uploaded image
  onFileSelected(files: FileList){
    this.photo.imageData = files[0];

    //add photo to post
    this.post.media = "photo";
    this.post.photos.push(this.photo.imageData);

  }

  


  sendPost() {
    this.post.userId = this.user;
      //creates new post
    this.postService.createNewPost(this.post).subscribe(
      data=> {
        this.post = data;
          //for now uploading a single photo. Change if implementing batch upload later
          //create a name based on the post id
          if(this.photo.imageData){
            console.log("Found image data");
            this.photo.photoString = "post_"+this.post.postId;
            this.photoService.uploadPhoto(this.photo).subscribe(
              data=>{
                this.photo = data
                this.refresh();
              } 
            );
          }else{
            this.refresh();
          } 
      }
    );
  }

  //refreshes page ro reload elements
  refresh(){
    this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
      this.router.navigate(['main']);
    });
  }

  handleFileInput(files: FileList) {
    for(let i =0; i<files.item.length; i++) {
        this.post.photos.push(files.item(i))
    }
  }

}
