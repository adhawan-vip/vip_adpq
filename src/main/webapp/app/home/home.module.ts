import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import {AccordionModule} from 'primeng/accordion';
import {CardModule} from 'primeng/card';
import { VipAdpqSharedModule } from '../shared';
import { HOME_ROUTE, HomeComponent } from './';

@NgModule({
    imports: [
        VipAdpqSharedModule,
        CardModule,
        AccordionModule,
        RouterModule.forChild([ HOME_ROUTE ])
    ],
    declarations: [
        HomeComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VipAdpqHomeModule {}
