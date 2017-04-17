import { Component, OnInit, Input } from '@angular/core';
import { Http, Response } from '@angular/http';
import { ActivatedRoute } from '@angular/router';
import { Author } from './author.model';
import { AuthorService } from './author.service';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {
  @Input() author:Author;
  id: number;
  @Input() isDataAvailable: boolean = false;
  
  constructor(private http: Http, private route: ActivatedRoute, private authorService: AuthorService) {
    route.params.subscribe(params => { this.id = params['id']; });
  }
  
  beforeInit(){
    if (this.id){
      this.http.get('http://localhost:8080/frontstepbystep-web/api/authors/' + this.id)
      .subscribe((res: Response) => {
        var temp = res.json();
        this.author = this.authorService.getAuthor(temp);
        this.isDataAvailable = true;
      });
    }
  }

  ngOnInit() {
    if(!this.isDataAvailable){
      this.beforeInit();
    }
  }

}
