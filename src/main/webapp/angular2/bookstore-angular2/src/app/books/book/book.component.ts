import { Component, OnInit, Input } from '@angular/core';
import { Book } from './book.model';
import { Editorial } from '../../editorial/editorial.model';
import { Http, Response } from '@angular/http';
import { ActivatedRoute } from '@angular/router';
import { BookService } from './book.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  id: number;
  @Input() book: Book;
  @Input() dataIsLoading: boolean;

  constructor(private http: Http, private route: ActivatedRoute, private bookService: BookService) { 
    route.params.subscribe(params => { this.id = params['id']; });
  }
  
  beforeInit(){
    if (this.id){
      this.http.get('http://localhost:8080/frontstepbystep-web/api/books/' + this.id)
      .subscribe((res: Response) => {
        var temp = res.json();
        this.book = this.bookService.getBook(temp);
      });
    }
  }

  ngOnInit() {
    if(!this.dataIsLoading){
      this.beforeInit();
    }
  }

}
