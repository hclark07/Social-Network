import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Post } from '../models/post';
import { Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostServicesService {

  constructor(private HttpCli: HttpClient, private router: Router) { }


  createNewPost(post: Post): Observable <Post>{
    //makes a request passing in new post information in url
    return this.HttpCli.post<Post>(`http://localhost:9005/social/api/createPost`,
    post, {withCredentials: true})
  }

}
