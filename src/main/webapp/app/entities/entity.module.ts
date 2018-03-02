import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { VipAdpqArticleModule } from './article/article.module';
import { VipAdpqTaskModule } from './task/task.module';
import { VipAdpqRelatedDocumentModule } from './related-document/related-document.module';
import { VipAdpqTaskOwnerModule } from './task-owner/task-owner.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        VipAdpqArticleModule,
        VipAdpqTaskModule,
        VipAdpqRelatedDocumentModule,
        VipAdpqTaskOwnerModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VipAdpqEntityModule {}
