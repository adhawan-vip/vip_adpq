import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TaskOwner } from './task-owner.model';
import { TaskOwnerService } from './task-owner.service';

@Component({
    selector: 'jhi-task-owner-detail',
    templateUrl: './task-owner-detail.component.html'
})
export class TaskOwnerDetailComponent implements OnInit, OnDestroy {

    taskOwner: TaskOwner;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private taskOwnerService: TaskOwnerService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTaskOwners();
    }

    load(id) {
        this.taskOwnerService.find(id)
            .subscribe((taskOwnerResponse: HttpResponse<TaskOwner>) => {
                this.taskOwner = taskOwnerResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTaskOwners() {
        this.eventSubscriber = this.eventManager.subscribe(
            'taskOwnerListModification',
            (response) => this.load(this.taskOwner.id)
        );
    }
}
