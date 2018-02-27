import { BaseEntity } from './../../shared';

export const enum ArticleType {
    'JOBAID',
    'CONTENT',
    'PACKAGE'
}

export const enum ArticleStatus {
    'DRAFT',
    'REVIEWED',
    'PUBLISHED'
}

export class Article implements BaseEntity {
    constructor(
        public id?: number,
        public articleName?: string,
        public publishDate?: any,
        public content?: any,
        public type?: ArticleType,
        public status?: ArticleStatus,
        public createdBy?: string,
        public createdOn?: any,
        public modifiedBy?: string,
        public modifiedOn?: any,
    ) {
    }
}
