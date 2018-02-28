import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { TaskOwnerComponent } from './task-owner.component';
import { TaskOwnerDetailComponent } from './task-owner-detail.component';
import { TaskOwnerPopupComponent } from './task-owner-dialog.component';
import { TaskOwnerDeletePopupComponent } from './task-owner-delete-dialog.component';

@Injectable()
export class TaskOwnerResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const taskOwnerRoute: Routes = [
    {
        path: 'task-owner',
        component: TaskOwnerComponent,
        resolve: {
            'pagingParams': TaskOwnerResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'vipAdpqApp.taskOwner.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'task-owner/:id',
        component: TaskOwnerDetailComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'vipAdpqApp.taskOwner.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const taskOwnerPopupRoute: Routes = [
    {
        path: 'task-owner-new',
        component: TaskOwnerPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'vipAdpqApp.taskOwner.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'task-owner/:id/edit',
        component: TaskOwnerPopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'vipAdpqApp.taskOwner.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'task-owner/:id/delete',
        component: TaskOwnerDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'vipAdpqApp.taskOwner.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
