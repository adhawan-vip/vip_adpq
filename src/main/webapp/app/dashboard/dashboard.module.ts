import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { VipAdpqBarchartModule } from './barchart/barchart.module';
import { VipAdpqDoughnutchartModule } from './doughnutchart/doughnutchart.module';
import { VipAdpqLinechartModule } from './linechart/linechart.module';
import { VipAdpqPiechartModule } from './piechart/piechart.module';
import { VipAdpqPolarareachartModule } from './polarareachart/polarareachart.module';
import { VipAdpqRadarchartModule } from './radarchart/radarchart.module';

@NgModule({
    imports: [
        VipAdpqBarchartModule,
        VipAdpqDoughnutchartModule,
        VipAdpqLinechartModule,
        VipAdpqPiechartModule,
        VipAdpqPolarareachartModule,
        VipAdpqRadarchartModule,
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class VipAdpqDashboardModule {}
