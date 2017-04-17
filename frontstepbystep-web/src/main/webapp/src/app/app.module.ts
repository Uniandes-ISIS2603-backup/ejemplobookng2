import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes} from '@angular/router';
import { routes as bookChildRoutes, BooksModule } from './books/books.module';
import { routes as authorChildRoutes, AuthorsModule } from './authors/authors.module';

import { AppComponent } from './app.component';
import { BooksComponent } from './books/books.component';
import { EditorialComponent } from './editorial/editorial.component';
import { AuthorsComponent } from './authors/authors.component';

const routes: Routes = [
  { path: '', redirectTo: 'books', pathMatch: 'full'},
  { path: 'books', component: BooksComponent, children: bookChildRoutes },
  { path: 'authors', component: AuthorsComponent, children: authorChildRoutes }
];

@NgModule({
  declarations: [
    AppComponent,
    EditorialComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(routes),
    BooksModule,
    AuthorsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
