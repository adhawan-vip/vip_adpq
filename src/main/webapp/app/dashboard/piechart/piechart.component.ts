import { Component, OnInit } from '@angular/core';
import { PieChartService } from './piechart.service';
import { HttpResponse } from '@angular/common/http';

@Component({
    selector: 'jhi-piechart',
    templateUrl: './piechart.component.html',
    styles: []
})
export class PiechartComponent implements OnInit {
    data: any;
    labels: any;
    pieData: any;
    constructor(private pieChartService: PieChartService) {
        this.load();
    }

    ngOnInit() {
    }

    load() {
        this.pieChartService.getPieLabels()
            .subscribe((res: HttpResponse<string[]>) => this.onSuccessLabels(res.body, res.headers));
        this.pieChartService.getPieData()
            .subscribe((res: HttpResponse<number[]>) => this.onSuccessData(res.body, res.headers));
    }

    private onSuccessLabels(data, headers) {
        this.labels = data;
        this.data = {
            labels: this.labels,
            datasets: [{
                data: [300, 50, 100],
                backgroundColor: [
                    '#C82333',
                    '#164675',
                    '#FDB81E'
                ],
                hoverBackgroundColor: [
                    '#dd3c4c',
                    '#1d5996',
                    '#fdc64e'
                ]
            }]
        };
    }
    
    private onSuccessData(pieData, headers) {
        console.log(pieData);
        this.pieData = pieData;
        this.data = {
            labels: this.labels,
            datasets: [{
                data: this.pieData,
                backgroundColor: [
                    '#C82333',
                    '#164675',
                    '#FDB81E'
                ],
                hoverBackgroundColor: [
                    '#dd3c4c',
                    '#1d5996',
                    '#fdc64e'
                ]
            }]
        };
    }
}
