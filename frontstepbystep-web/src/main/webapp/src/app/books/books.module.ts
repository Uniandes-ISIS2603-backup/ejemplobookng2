import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  RouterModule,
  Routes
} from '@angular/router';
import { BookService } from './book/book.service';
import { BooksComponent } from '../books/books.component';
import { BookComponent } from './book/book.component';
import { BookListComponent } from './book-list/book-list.component';

export const routes: Routes = [
  { path: '', redirectTo: 'bookList', pathMatch: 'full'},
  { path: 'bookList', component: BookListComponent },
  { path: 'book/:id', component: BookComponent }
];

@NgModule({
  declarations: [
    BookComponent,
    BookListComponent,
    BooksComponent
  ],
  
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    BookComponent,
    BookListComponent,
    BooksComponent
  ],
  providers: [
    BookService
  ]
})
export class BooksModule { }

