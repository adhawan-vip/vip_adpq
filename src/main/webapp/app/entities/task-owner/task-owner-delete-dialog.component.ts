import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TaskOwner } from './task-owner.model';
import { TaskOwnerPopupService } from './task-owner-popup.service';
import { TaskOwnerService } from './task-owner.service';

@Component({
    selector: 'jhi-task-owner-delete-dialog',
    templateUrl: './task-owner-delete-dialog.component.html'
})
export class TaskOwnerDeleteDialogComponent {

    taskOwner: TaskOwner;

    constructor(
        private taskOwnerService: TaskOwnerService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.taskOwnerService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'taskOwnerListModification',
                content: 'Deleted an taskOwner'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-task-owner-delete-popup',
    template: ''
})
export class TaskOwnerDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private taskOwnerPopupService: TaskOwnerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.taskOwnerPopupService
                .open(TaskOwnerDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
