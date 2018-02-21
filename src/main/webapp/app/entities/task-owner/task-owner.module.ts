import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VipAdpqSharedModule } from '../../shared';
import {
    TaskOwnerService,
    TaskOwnerPopupService,
    TaskOwnerComponent,
    TaskOwnerDetailComponent,
    TaskOwnerDialogComponent,
    TaskOwnerPopupComponent,
    TaskOwnerDeletePopupComponent,
    TaskOwnerDeleteDialogComponent,
    taskOwnerRoute,
    taskOwnerPopupRoute,
    TaskOwnerResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...taskOwnerRoute,
    ...taskOwnerPopupRoute,
];

@NgModule({
    imports: [
        VipAdpqSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TaskOwnerComponent,
        TaskOwnerDetailComponent,
        TaskOwnerDialogComponent,
        TaskOwnerDeleteDialogComponent,
        TaskOwnerPopupComponent,
        TaskOwnerDeletePopupComponent,
    ],
    entryComponents: [
        TaskOwnerComponent,
        TaskOwnerDialogComponent,
        TaskOwnerPopupComponent,
        TaskOwnerDeleteDialogComponent,
        TaskOwnerDeletePopupComponent,
    ],
    providers: [
        TaskOwnerService,
        TaskOwnerPopupService,
        TaskOwnerResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VipAdpqTaskOwnerModule {}
