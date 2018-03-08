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

    ngOnInit() {
    }
}
