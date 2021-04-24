import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserServicesService } from '../services/user-services.service';
import { Pipe, PipeTransform } from '@angular/core';
import { templateJitUrl } from '@angular/compiler';

//@Pipe({ name: 'appFilter' })

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit  {

  constructor(private userService: UserServicesService) { }
  user: User;   


  searchText = '';
  userFriends = []  
  filteredItems = [];


  ngOnInit(): void {
    this.loadFriendList();
    this.currentUser();
  }


  currentUser() {
    console.log("Grabbing current user session form nav bar")
    //subscribes to user returned from get user service and saves in component
    this.user = this.userService.getCurrentUserSession()
  }



  loadFriendList() {
    //subscribes to list of friends returned and saves in an array
    this.userService.getFriendList().subscribe(
      data=> {
        this.userFriends = data;
        console.log(this.userFriends)
      }
    )
  }

//takes input as value from html
 filterItem(value){
   //sets array empty if nothing is typed in
    if(value == ''){
        this.filteredItems = null;
    }//filters friend list based on value typed in
    else{
    this.filteredItems = this.userFriends.filter(word => word.username.indexOf(value) > -1);
    }
 }
}
