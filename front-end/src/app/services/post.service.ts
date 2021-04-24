import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Likes } from '../models/likes';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private HttpCli: HttpClient) { }

  post: Post = {
    postId: 0,
    description: '',
    photos: [],
    media: '',
    userId: null,
    users: []
  };
  
  getAllPosts(): Observable<Post[]> {

    return this.HttpCli.get<Post[]>(`http://localhost:9005/social/api/getAllPosts/`,
    {withCredentials: true});

  }

  
  addLike(like: Likes){
    this.HttpCli.post(`http://localhost:9005/social/api/likePost`, like
    , {withCredentials: true}).toPromise()
  } 

  unLike(like: Likes){

    this.HttpCli.put(`http://localhost:9005/social/api/unlikePost`, like
    , {withCredentials: true}).toPromise()
  }

  getUserPosts(userId): Observable<Post[]> {
   
    const post= this.HttpCli.get<Post[]>(`http://localhost:9005/social/api/getPostsByUserId/?id=${userId}`,
    {withCredentials: true})
    
    return post;
  }

  createNewPost(post: Post): Observable <Post>{
    console.log("in the create new post method service")
    return this.HttpCli.post<Post>(`http://localhost:9005/social/api/post/create`,
    post, {withCredentials: true})
  }
}
