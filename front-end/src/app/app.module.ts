import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { NewPostComponent } from './new-post/new-post.component';
import { NewUserComponent } from './new-user/new-user.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ProfileComponent } from './profile/profile.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LeftContainerComponent } from './left-container/left-container.component';
import { PostsComponent } from './posts/posts.component';
import { FriendListComponent } from './friend-list/friend-list.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { ChatComponent } from './chat/chat.component';
import { EmailPasswordComponent } from './loginpage/email-password/email-password.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    LoginpageComponent,
    NewPostComponent,
    NewUserComponent,
    MainpageComponent,
    ProfileComponent,
    NavbarComponent,
    LeftContainerComponent,
    PostsComponent,
    FriendListComponent,
    UpdateUserComponent,
    ChatComponent,
    EmailPasswordComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
