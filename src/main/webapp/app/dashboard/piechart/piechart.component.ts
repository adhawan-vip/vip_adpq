import { Component, OnInit } from '@angular/core';
import { JhiLanguageService } from 'ng-jhipster';

@Component({
    selector: 'jhi-piechart',
    templateUrl: './piechart.component.html',
    styles: []
})
export class PiechartComponent implements OnInit {
    data: any;

    constructor() {
        this.data = {
            labels: ['JOBAID', 'CONTENT', 'PACKAGE'],
            datasets: [{
                data: [300, 50, 100],
                backgroundColor: [
                    '#FF6384',
                    '#164675',
                    '#FDB81E'
                ],
                hoverBackgroundColor: [
                    '#FF6384',
                    '#36A2EB',
                    '#FFCE56'
                ]
            }]
        };
    }

    ngOnInit() {
    }
}
