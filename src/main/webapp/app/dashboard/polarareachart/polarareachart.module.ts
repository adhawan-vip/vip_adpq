import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VipAdpqSharedModule } from '../../shared';
import { ChartModule } from 'primeng/chart';

import {
    PolarareachartComponent,
    polarareachartRoute
} from './';

const DASHBOARD_STATES = [
    polarareachartRoute
];

@NgModule({
    imports: [
        VipAdpqSharedModule,
        ChartModule,
        RouterModule.forRoot(DASHBOARD_STATES, { useHash: true })
    ],
    declarations: [
        PolarareachartComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VipAdpqPolarareachartModule {}
