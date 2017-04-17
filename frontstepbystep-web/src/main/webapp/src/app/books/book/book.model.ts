import { Editorial } from '../../editorial/editorial.model';
export class Book {
    constructor(
        public id: number,
        public description: string,
        public image: string,
        public ISBN: string,
        public name: string,
        public publishdate: Date,
        public editorial: Editorial
    ){
        
    }
}