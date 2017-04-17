import { Component, OnInit, ViewEncapsulation  } from '@angular/core';
import { Http, Response } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Author } from '../author/author.model';
import { AuthorService } from '../author/author.service';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements OnInit {
  http: Http;
  authors: Author[] = new Array();
  
  constructor(http: Http, private router: Router, private route: ActivatedRoute, private authorService: AuthorService ) { 
    this.http = http;
    this.beforeInit();
  }
  
  beforeInit() {
    this.http.get('http://localhost:8080/frontstepbystep-web/api/authors')
      .subscribe((res: Response) => {
        //let book = new Book();
        let temp = res.json();
        for(var i = 0; i < temp.length; i++){
          var author: Author = this.authorService.getAuthor(temp[i]);
          this.authors.push(author);
        }
      });
  }
  
  goToAuthor (id:number){
    this.router.navigate(['/authors/author/', id], {relativeTo: this.route});
  }

  ngOnInit() {
    
  }

}
