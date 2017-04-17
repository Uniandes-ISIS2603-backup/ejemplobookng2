import { Injectable } from '@angular/core';
import { Author } from './author.model';
import { BookService } from '../../books/book/book.service';
import { Book } from '../../books/book/book.model';

@Injectable()
export class AuthorService {
  constructor(private bookService: BookService){}
  
  public getAuthor(author:any){
    let books: Book[] = new Array();
    for(var i = 0; i < author.books.length; i++){
      books.push(this.bookService.getBook(author.books[i]));
    }
    return new Author(author.id, new Date(author.birthDate), author.description, author.image, author.name, books);
  }
}
