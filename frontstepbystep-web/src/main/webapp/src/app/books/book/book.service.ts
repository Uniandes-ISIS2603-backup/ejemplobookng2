import { Injectable } from '@angular/core';
import { Editorial } from '../../editorial/editorial.model';
import { Book } from './book.model';

@Injectable()
export class BookService {
  constructor() {}
  getBook(origin:any){
    let editorial: Editorial = new Editorial(origin.editorial.id, origin.editorial.name);
    return new Book(origin.id, origin.description, origin.image, origin.isbn, origin.name, new Date(origin.publishingdate), editorial);
  }
}