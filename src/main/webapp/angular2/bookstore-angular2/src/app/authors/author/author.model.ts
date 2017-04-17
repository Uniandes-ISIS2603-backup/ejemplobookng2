import { Book } from '../../books/book/book.model';
export class Author {
    constructor(
        public id: number,
        public birthdate: Date,
        public description: string,
        public image: string,
        public name: string,
        public book: Book[]
    ){
        
    }
}