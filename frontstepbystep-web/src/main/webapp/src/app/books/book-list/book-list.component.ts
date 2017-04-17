import {Component, OnInit, ViewEncapsulation, Input } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Book } from '../book/book.model';
import { BookService } from '../book/book.service';
import {
  ActivatedRoute,
  Router
} from '@angular/router';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  http: Http;
  @Input() books: Book[] = new Array();
  
  constructor(http: Http, private router: Router, private route: ActivatedRoute, private bookService: BookService) {
    this.http = http;
  }
  
  goToBook (id:number){
    this.router.navigate(['/books/book/', id], {relativeTo: this.route});
  }
  
  beforeInit (){
    this.http.get('http://localhost:8080/frontstepbystep-web/api/books')
      .subscribe((res: Response) => {
        let temp = res.json();
        var bookTemporal: Book;
        this.books = new Array();
        
        for(var i = 0; i < temp.length; i++){
          bookTemporal = this.bookService.getBook(temp[i]);
          this.books.push(bookTemporal);
        }
      });
  }

  ngOnInit() {
    if (!this.books.length){
      this.beforeInit();
    }
  }

}
