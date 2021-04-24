import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Photo } from '../models/photo';
import { User } from '../models/user';
import { PhotoServicesService } from '../services/photo-services.service';
import { UserServicesService } from '../services/user-services.service';


@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  //user information
  user: User = {userId: 0,
    fname: "",
    lname: '',
    email: '',
    password: '',
    username: '',
    avatar: '',
    userDescription: '',
    posts: []
  }

  //photo information
  photo: Photo = {
    photoId: 0,
    photoString: '',
    // post: null,
    imageData: null
  };


  constructor(private userService: UserServicesService, private photoService: PhotoServicesService, private router: Router) { }

  ngOnInit(): void {
    this.currentUser();
  }

  get emailField () {
    return this.user.email;
  }

  set emailField (str: string) {
    this.user.email = str;
  }

  get passwordField () {
    return this.user.password;
  }

  set passwordField(str: string) {
    this.user.password = str;
  }
  get usernameField () {
    return this.user.username;
  }

  set usernameField (str: string) {
    this.user.username = str;
  }

  get fnameField () {
    return this.user.fname;
  }

  set fnameField(str: string) {
    this.user.fname = str;
  }
  
  get lnameField () {
    return this.user.lname;
  }

  set lnameField(str: string) {
    this.user.lname = str;
  }

  get descriptionField () {
    return this.user.userDescription;
  }

  set descriptionField(str: string) {
    this.user.userDescription = str;
  }


  //finds current user
  currentUser() {
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
        console.log(this.user)
      }
    )
  }

  //getting uploaded photo
  onFileSelected(file){
    this.photo.imageData = <File>file[0];
    console.log(this.photo);
  }

  uploadPhoto(){
    console.log("In uploadPhoto");
  }


   onSubmit() {
    //If a photo was uploaded
    if(this.photo.imageData){
      //create a name based on the user id
      this.photo.photoString = "avatar_"+this.user.userId;
      //upload photo
      this.photoService.uploadPhoto(this.photo).subscribe(
        data=> {
          this.photo = data;
        }
      )
      //add photo to user here
      this.user.avatar = "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/"+this.photo.photoString;
      console.log("Photo ref link = "+ this.user.avatar);
    }
    console.log(this.user)
    this.userService.updateUser(this.user)

  }

  onBackButton() {
    this.router.navigate(['/main']);
  }

}
