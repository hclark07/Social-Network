import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ChatMessage } from '../models/chatMessage';
import { WebsocketService } from '../services/websocket.service';
import { UserServicesService } from '../services/user-services.service';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {


  user: string;
  date = formatDate(new Date(), 'full', 'en');


  constructor(public webSocketService: WebsocketService, public userService: UserServicesService) { 
    
  }

  ngOnInit(): void {
    this.webSocketService.openWebSocket();
    this.getUser();
  }

  ngOnDestroy(): void {
    this.webSocketService.closeWebSocket();
  }

  getUser(){

    this.userService.getUserSession().subscribe(data => {
      this.user = data.username;
    })
  }


  sendMessage(sendForm: NgForm){

    const chatMessageObject = new ChatMessage(this.user, sendForm.value.message)
    this.webSocketService.sendMessage(chatMessageObject);
    sendForm.resetForm();
  }

}

