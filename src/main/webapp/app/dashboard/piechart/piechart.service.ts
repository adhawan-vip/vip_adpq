import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

export type EntityResponseType = HttpResponse<string>;

@Injectable()
export class PieChartService {
    private resourceUrl = SERVER_API_URL + 'api';
    private pieLabelsUrl = this.resourceUrl + '/labels/pie';

    constructor(private http: HttpClient) { }

    getPieLabels(): Observable<HttpResponse<string[]>> {
        return this.http.get<string[]>(`${this.pieLabelsUrl}`, { observe: 'response' })
            .map((res: HttpResponse<string[]>) => this.convertArrayResponse(res));
    }

    private convertArrayResponse(res: HttpResponse<string[]>): HttpResponse<string[]> {
        const jsonResponse: string[] = res.body;
        const body: string[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(jsonResponse[i]);
        }
        return res.clone({ body });
    }
}
