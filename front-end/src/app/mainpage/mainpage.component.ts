import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserServicesService } from '../services/user-services.service';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {
  user: User;

  constructor(private userService: UserServicesService) { }

  ngOnInit() {
    this.currentUser();
    this.getAllUsers();
  }

  //retrieves current user from user service
  currentUser() {
    console.log("Setting current user session from Main component")
    //subscribes to value returned from current user service
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
        this.userService.setCurrentUserSession(this.user)
      }
    )
  }

  getAllUsers() {
    this.userService.getFriendsList();
  }
}
