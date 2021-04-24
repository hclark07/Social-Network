import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserServicesService } from '../services/user-services.service';

@Component({
  selector: 'app-friend-list',
  templateUrl: './friend-list.component.html',
  styleUrls: ['./friend-list.component.css']
})
export class FriendListComponent implements OnInit {

  userFriends: User[]

  constructor(private userService: UserServicesService) { }

  ngOnInit(): void {
    this.getFriendList();
  }

  getFriendList() {
    this.userService.getFriendsList().subscribe(
      data => {
        this.userFriends = data
      }
    )
  }

}
