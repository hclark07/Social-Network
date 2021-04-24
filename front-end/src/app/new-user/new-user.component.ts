import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FormGroup, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServicesService } from '../services/user-services.service';
import { User } from '../models/user';




@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit{

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserServicesService) {}
  
  currentUser: User = {userId: 0,
    fname: "",
    lname: '',
    email: '',
    password: '',
    username: '',
    avatar: '',
    userDescription:'',
    posts: []
  }

  profileForm = new FormGroup({
    email: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
  });

  ngOnInit(): void {

  }

  //sets current user equal to whatever was typed in in html
  //sends to create user service
   onSubmit() {
    event.preventDefault();
    this.currentUser.fname = this.profileForm.value.firstName;
    this.currentUser.lname = this.profileForm.value.lastName;
    this.currentUser.username = this.profileForm.value.username;
    this.currentUser.password = this.profileForm.value.password;
    this.currentUser.email = this.profileForm.value.email;

    //this.router.navigate(['']);
    this.userService.createUser(this.currentUser)
  }

}
