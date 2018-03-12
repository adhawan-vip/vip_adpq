import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

export type EntityResponseType = HttpResponse<string>;

@Injectable()
export class PieChartService {
    private resourceUrl = SERVER_API_URL + 'api';
    private pieLabelsUrl = this.resourceUrl + '/labels/pie';
    private pieDataUrl = this.resourceUrl + '/data/pie';
    private pieStatusLabelsUrl = this.resourceUrl + '/labels/pie/status';
    private pieStatusDataUrl = this.resourceUrl + '/data/pie/status';

    constructor(private http: HttpClient) { }

    getPieLabels(): Observable<HttpResponse<string[]>> {
        return this.http.get<string[]>(`${this.pieLabelsUrl}`, { observe: 'response' })
            .map((res: HttpResponse<string[]>) => this.convertArrayResponse(res));
    }

    getPieData(): Observable<HttpResponse<number[]>> {
        return this.http.get<number[]>(`${this.pieDataUrl}`, { observe: 'response' })
            .map((res: HttpResponse<number[]>) => this.convertArrayResponseData(res));
    }

    getPieStatusLabels(): Observable<HttpResponse<string[]>> {
        console.log('here');
        return this.http.get<string[]>(`${this.pieStatusLabelsUrl}`, { observe: 'response' })
            .map((res: HttpResponse<string[]>) => this.convertArrayResponse(res));
    }

    getPieStatusData(): Observable<HttpResponse<number[]>> {
        return this.http.get<number[]>(`${this.pieStatusDataUrl}`, { observe: 'response' })
            .map((res: HttpResponse<number[]>) => this.convertArrayResponseData(res));
    }

    private convertArrayResponse(res: HttpResponse<string[]>): HttpResponse<string[]> {
        const jsonResponse: string[] = res.body;
        const body: string[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(jsonResponse[i]);
        }
        return res.clone({ body });
    }

     private convertArrayResponseData(res: HttpResponse<number[]>): HttpResponse<number[]> {
        const jsonResponse: number[] = res.body;
        const body: number[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(jsonResponse[i]);
        }
        return res.clone({ body });
    }
}
