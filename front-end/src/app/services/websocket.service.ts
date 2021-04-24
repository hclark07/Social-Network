import { JsonpClientBackend } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ChatMessage } from '../models/chatMessage';


@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

webSocket: WebSocket;
chatMessages: ChatMessage[] = [];

  constructor() { }


  public openWebSocket(){
    //creates new websocket ince
    this.webSocket = new WebSocket('ws://localhost:9005/social/chat');

    this.webSocket.onopen = (event) => {
      console.log('Open: ', event)
    }

    //handles chat messages
    this.webSocket.onmessage = (event) => {
        const chatMessageDto = JSON.parse(event.data)
        this.chatMessages.push(chatMessageDto);
    }

    this.webSocket.onclose = (event) => {
      console.log('Close: ', event)
    }
  }

  public sendMessage(chatMessage: ChatMessage){
    this.webSocket.send(JSON.stringify(chatMessage))
  }

  public closeWebSocket(){
    this.webSocket.close();
  }

}
