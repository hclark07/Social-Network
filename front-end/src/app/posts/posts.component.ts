import { Component, OnInit } from '@angular/core';
import { Likes } from '../models/likes';
import { Post } from '../models/post';
import { User } from '../models/user';
import { PostService } from '../services/post.service';
import { UserServicesService } from '../services/user-services.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  post: Post = {
    postId: 0,
    description: '',
    photos: [],
    media: '',
    userId: null,
    users: []
  }

  allPosts: Post[];
  loading: boolean = true;
  like: Likes;
  liked: boolean;

  user: User;

  constructor(private userService: UserServicesService, private postService: PostService) {

   }

  ngOnInit(): void {
    
    this.currentUser();
    this.allPost();

  }


  currentUser() {
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
      }
    )
  }

  likePost(post: Post){
    this.like = {
      postId: post.postId,
      userId: this.user.userId
    }
    
    ///Find the userId that matches current user's id:

   let position = post.users.map(x => {
      return x.userId;
    }).indexOf(this.user.userId)
    console.log(position)
    console.log(post)

   if(position > 0){
     this.liked = true;
   } else {
     this.liked = false;
   }
    ///If the userId doesn't exist in the post's users array then add

    if(position === -1 && !this.liked){
      console.log("adding the like")
      post.users.push(this.user);
      this.postService.addLike(this.like)
      this.liked = true;
    }

    ///If the userId does exist then remove it from the array

    if(position > -1){
      post.users.splice(position);
      this.postService.unLike(this.like);
      this.liked = false;
    }
    
  }


  ///Return all the posts for the feed

  allPost(){
    console.log("Grab All post method")
    this.postService.getAllPosts().subscribe(
      postData => {
        console.log(postData);
        this.allPosts = postData;
        this.loading = false;
      }
    )
  }

  //open image modal

  openModal(id){
    console.log("Clicked photo for post "+ id);
    let modal = document.getElementById("postModal");
    let img = document.getElementById("image_"+id) as HTMLImageElement;
    let modalImg = document.getElementById("floatingImg") as HTMLImageElement;
    modal.style.display = "block";
    modalImg.src = img.src;
  }

  //close image modal
  
  closeModal(){
    let modal = document.getElementById("postModal");
    modal.style.display = "none";
  }

}
