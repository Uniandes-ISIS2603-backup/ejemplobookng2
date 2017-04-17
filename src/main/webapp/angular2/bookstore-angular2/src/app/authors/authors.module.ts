import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  RouterModule,
  ActivatedRoute,
  Router,
  Routes
} from '@angular/router';

import { AuthorsComponent } from './authors.component';
import { AuthorComponent } from './author/author.component';
import { AuthorListComponent } from './author-list/author-list.component';
import { AuthorService } from './author/author.service';
import { BooksModule } from '../books/books.module';

export const routes: Routes = [
  { path: '', redirectTo: 'authorlist', pathMatch: 'full'},
  { path: 'authorlist', component: AuthorListComponent },
  { path: 'author/:id', component: AuthorComponent }
];

@NgModule({
  declarations: [
    AuthorComponent,
    AuthorListComponent,
    AuthorsComponent
  ],
  
  imports: [
    CommonModule,
    RouterModule,
    BooksModule
  ],
  exports: [
    AuthorComponent,
    AuthorListComponent
  ],
  providers: [
    AuthorService
  ]
})
export class AuthorsModule { }

