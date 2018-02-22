import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { TaskOwner } from './task-owner.model';
import { TaskOwnerService } from './task-owner.service';

@Injectable()
export class TaskOwnerPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private taskOwnerService: TaskOwnerService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.taskOwnerService.find(id)
                    .subscribe((taskOwnerResponse: HttpResponse<TaskOwner>) => {
                        const taskOwner: TaskOwner = taskOwnerResponse.body;
                        this.ngbModalRef = this.taskOwnerModalRef(component, taskOwner);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.taskOwnerModalRef(component, new TaskOwner());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    taskOwnerModalRef(component: Component, taskOwner: TaskOwner): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.taskOwner = taskOwner;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
