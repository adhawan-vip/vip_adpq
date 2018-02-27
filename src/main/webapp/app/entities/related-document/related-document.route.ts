import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { RelatedDocumentComponent } from './related-document.component';
import { RelatedDocumentDetailComponent } from './related-document-detail.component';
import { RelatedDocumentPopupComponent } from './related-document-dialog.component';
import { RelatedDocumentDeletePopupComponent } from './related-document-delete-dialog.component';

@Injectable()
export class RelatedDocumentResolvePagingParams implements Resolve<any> {

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

export const relatedDocumentRoute: Routes = [
    {
        path: 'related-document',
        component: RelatedDocumentComponent,
        resolve: {
            'pagingParams': RelatedDocumentResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_AUTHOR', 'ROLE_REVIEWER'],
            pageTitle: 'vipAdpqApp.relatedDocument.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'related-document/:id',
        component: RelatedDocumentDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_AUTHOR', 'ROLE_REVIEWER'],
            pageTitle: 'vipAdpqApp.relatedDocument.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const relatedDocumentPopupRoute: Routes = [
    {
        path: 'related-document-new',
        component: RelatedDocumentPopupComponent,
        data: {
            authorities: ['ROLE_AUTHOR'],
            pageTitle: 'vipAdpqApp.relatedDocument.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'related-document/:id/edit',
        component: RelatedDocumentPopupComponent,
        data: {
            authorities: ['ROLE_AUTHOR', 'ROLE_REVIEWER'],
            pageTitle: 'vipAdpqApp.relatedDocument.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'related-document/:id/delete',
        component: RelatedDocumentDeletePopupComponent,
        data: {
            authorities: ['ROLE_AUTHOR', 'ROLE_REVIEWER'],
            pageTitle: 'vipAdpqApp.relatedDocument.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
