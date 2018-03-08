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
    constructor(private pieChartService: PieChartService) {
        this.load();
    }

    ngOnInit() {
    }

    load() {
        this.pieChartService.getPieLabels()
            .subscribe((res: HttpResponse<string[]>) => this.onSuccess(res.body, res.headers));
    }

    private onSuccess(data, headers) {
        this.data = {
            labels: data,
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
}
