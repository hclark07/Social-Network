import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewUserComponent } from './new-user/new-user.component';
import { LoginpageComponent} from './loginpage/loginpage.component';
import { EmailPasswordComponent} from './loginpage/email-password/email-password.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ProfileComponent } from './profile/profile.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { ChatComponent } from './chat/chat.component';


const routes: Routes = [
  { path: 'update-user', component: UpdateUserComponent, data: { animation: 'evenRighter' } },
  {path: 'forgotPassword', component: EmailPasswordComponent, data: { animation: 'evenRighter' }},
  { path: 'chat', component: ChatComponent, data: { animation: 'evenRighter' } },
  { path: 'new-user', component: NewUserComponent, data: { animation: 'isRight' } },
  { path: 'main', component: MainpageComponent, data: { animation: 'isRight' }},
  { path: 'profile/:username/:id', component: ProfileComponent, data: { animation: 'righter' }},
  { path: '**', component: LoginpageComponent, data: { animation: 'isLeft' } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
