import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TaskOwner } from './task-owner.model';
import { TaskOwnerPopupService } from './task-owner-popup.service';
import { TaskOwnerService } from './task-owner.service';

@Component({
    selector: 'jhi-task-owner-dialog',
    templateUrl: './task-owner-dialog.component.html'
})
export class TaskOwnerDialogComponent implements OnInit {

    taskOwner: TaskOwner;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private taskOwnerService: TaskOwnerService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.taskOwner.id !== undefined) {
            this.subscribeToSaveResponse(
                this.taskOwnerService.update(this.taskOwner));
        } else {
            this.subscribeToSaveResponse(
                this.taskOwnerService.create(this.taskOwner));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TaskOwner>>) {
        result.subscribe((res: HttpResponse<TaskOwner>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TaskOwner) {
        this.eventManager.broadcast({ name: 'taskOwnerListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-task-owner-popup',
    template: ''
})
export class TaskOwnerPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private taskOwnerPopupService: TaskOwnerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.taskOwnerPopupService
                    .open(TaskOwnerDialogComponent as Component, params['id']);
            } else {
                this.taskOwnerPopupService
                    .open(TaskOwnerDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
