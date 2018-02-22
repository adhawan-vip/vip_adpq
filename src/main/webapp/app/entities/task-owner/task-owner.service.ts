import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TaskOwner } from './task-owner.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TaskOwner>;

@Injectable()
export class TaskOwnerService {

    private resourceUrl =  SERVER_API_URL + 'api/task-owners';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/task-owners';

    constructor(private http: HttpClient) { }

    create(taskOwner: TaskOwner): Observable<EntityResponseType> {
        const copy = this.convert(taskOwner);
        return this.http.post<TaskOwner>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(taskOwner: TaskOwner): Observable<EntityResponseType> {
        const copy = this.convert(taskOwner);
        return this.http.put<TaskOwner>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TaskOwner>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TaskOwner[]>> {
        const options = createRequestOption(req);
        return this.http.get<TaskOwner[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TaskOwner[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<TaskOwner[]>> {
        const options = createRequestOption(req);
        return this.http.get<TaskOwner[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TaskOwner[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TaskOwner = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TaskOwner[]>): HttpResponse<TaskOwner[]> {
        const jsonResponse: TaskOwner[] = res.body;
        const body: TaskOwner[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TaskOwner.
     */
    private convertItemFromServer(taskOwner: TaskOwner): TaskOwner {
        const copy: TaskOwner = Object.assign({}, taskOwner);
        return copy;
    }

    /**
     * Convert a TaskOwner to a JSON which can be sent to the server.
     */
    private convert(taskOwner: TaskOwner): TaskOwner {
        const copy: TaskOwner = Object.assign({}, taskOwner);
        return copy;
    }
}
