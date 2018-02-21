import { BaseEntity } from './../../shared';

export class RelatedDocument implements BaseEntity {
    constructor(
        public id?: number,
        public docName?: string,
        public docFileContentType?: string,
        public docFile?: any,
        public articleId?: number,
    ) {
    }
}
