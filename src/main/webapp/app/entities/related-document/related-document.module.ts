import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VipAdpqSharedModule } from '../../shared';
import {
    RelatedDocumentService,
    RelatedDocumentPopupService,
    RelatedDocumentComponent,
    RelatedDocumentDetailComponent,
    RelatedDocumentDialogComponent,
    RelatedDocumentPopupComponent,
    RelatedDocumentDeletePopupComponent,
    RelatedDocumentDeleteDialogComponent,
    relatedDocumentRoute,
    relatedDocumentPopupRoute,
    RelatedDocumentResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...relatedDocumentRoute,
    ...relatedDocumentPopupRoute,
];

@NgModule({
    imports: [
        VipAdpqSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        RelatedDocumentComponent,
        RelatedDocumentDetailComponent,
        RelatedDocumentDialogComponent,
        RelatedDocumentDeleteDialogComponent,
        RelatedDocumentPopupComponent,
        RelatedDocumentDeletePopupComponent,
    ],
    entryComponents: [
        RelatedDocumentComponent,
        RelatedDocumentDialogComponent,
        RelatedDocumentPopupComponent,
        RelatedDocumentDeleteDialogComponent,
        RelatedDocumentDeletePopupComponent,
    ],
    providers: [
        RelatedDocumentService,
        RelatedDocumentPopupService,
        RelatedDocumentResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VipAdpqRelatedDocumentModule {}
