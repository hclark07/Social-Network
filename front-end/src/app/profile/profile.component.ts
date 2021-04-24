import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Event as NavigationEvent, NavigationStart, NavigationEnd } from '@angular/router'
import { Subscription } from 'rxjs';
import { Likes } from '../models/likes';
import { Post } from '../models/post';
import { User } from '../models/user';
import { PostService } from '../services/post.service';
import { UserServicesService } from '../services/user-services.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  userFriends: User[]
  private id: number;
  user: User;
  allPosts: Post[];
  show: boolean = false;
  private _routerSub = Subscription.EMPTY;
  like: Likes;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserServicesService, private postService: PostService) { 
    this._routerSub= this.router.events.subscribe(
    (event: NavigationEvent) => {
      if(event instanceof NavigationEnd) {
          this.id = Number(this.route.snapshot.paramMap.get('id'))
          this.userService.setCurrentProfileView(this.id);
      }
      
    });
  }

  ngOnInit(): void {
    this.getFriendList();
    this.getUsersPosts();
    this.getUserSession();
  }

  async getUsersPosts(){
    
    this.postService.getAllPosts().subscribe(posts =>{
      this.allPosts = posts
      // .filter(x => x.userId.userId == this.id);
    }
      
    )
  }

  getUserSession(){
    this.userService.getUserSession().subscribe(session => {
      this.user = session;
    })
  }

  getFriendList() {
    this.userService.getFriendsList().subscribe(
      data => {
        this.userFriends = data
      }
    )
  }

  ngOnDestroy(){
    this._routerSub.unsubscribe();
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
     this.show = true;
   } else {
     this.show  = false;
   }
    ///If the userId doesn't exist in the post's users array then add

    if(position === -1 && !this.show ){
      console.log("adding the like")
      post.users.push(this.user);
      this.postService.addLike(this.like)
      this.show  = true;
    }

    ///If the userId does exist then remove it from the array

    if(position > -1){
      post.users.splice(position);
      this.postService.unLike(this.like);
      this.show  = false;
    }
    
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
