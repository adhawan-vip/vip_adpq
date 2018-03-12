import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import {CardModule} from 'primeng/card';
import { VipAdpqSharedModule } from '../../shared';
import { ChartModule } from 'primeng/chart';

import {
    PiechartComponent,
    piechartRoute
} from './';

import { PieChartService } from './piechart.service';

const DASHBOARD_STATES = [
    piechartRoute
];

@NgModule({
    imports: [
        VipAdpqSharedModule,
        ChartModule,
        CardModule,
        RouterModule.forRoot(DASHBOARD_STATES, { useHash: true })
    ],
    declarations: [
        PiechartComponent
    ],
    providers: [
        PieChartService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VipAdpqPiechartModule {}
