import { BaseEntity } from './../../shared';

export class Article implements BaseEntity {
    constructor(
        public id?: number,
        public articleName?: string,
        public publishDate?: any,
        public content?: string,
        public fileContentType?: string,
        public file?: any,
    ) {
    }
}
