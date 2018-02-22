import { BaseEntity } from './../../shared';

export class TaskOwner implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public email?: string,
    ) {
    }
}
