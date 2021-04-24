import { User } from "./user";

export interface Post{
    postId: number,
    description: string,
    photos: File[],
    media: string,
    userId: User
    users:  User[];
}