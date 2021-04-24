import { Post } from "./post";

export interface Photo{
    photoId: number,
    photoString: string,
    // post: Post,
    imageData: File
}