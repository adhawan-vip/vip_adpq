import { BaseEntity } from './../../shared';

export const enum TaskStatus {
    'OPEN',
    'CLOSED'
}

export class Task implements BaseEntity {
    constructor(
        public id?: number,
        public taskName?: string,
        public dueDate?: any,
        public description?: string,
        public status?: TaskStatus,
        public ownerId?: number,
        public articleId?: number,
    ) {
    }
}
