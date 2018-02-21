import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { RelatedDocument } from './related-document.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<RelatedDocument>;

@Injectable()
export class RelatedDocumentService {

    private resourceUrl =  SERVER_API_URL + 'api/related-documents';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/related-documents';

    constructor(private http: HttpClient) { }

    create(relatedDocument: RelatedDocument): Observable<EntityResponseType> {
        const copy = this.convert(relatedDocument);
        return this.http.post<RelatedDocument>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(relatedDocument: RelatedDocument): Observable<EntityResponseType> {
        const copy = this.convert(relatedDocument);
        return this.http.put<RelatedDocument>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<RelatedDocument>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<RelatedDocument[]>> {
        const options = createRequestOption(req);
        return this.http.get<RelatedDocument[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<RelatedDocument[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<RelatedDocument[]>> {
        const options = createRequestOption(req);
        return this.http.get<RelatedDocument[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<RelatedDocument[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: RelatedDocument = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<RelatedDocument[]>): HttpResponse<RelatedDocument[]> {
        const jsonResponse: RelatedDocument[] = res.body;
        const body: RelatedDocument[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to RelatedDocument.
     */
    private convertItemFromServer(relatedDocument: RelatedDocument): RelatedDocument {
        const copy: RelatedDocument = Object.assign({}, relatedDocument);
        return copy;
    }

    /**
     * Convert a RelatedDocument to a JSON which can be sent to the server.
     */
    private convert(relatedDocument: RelatedDocument): RelatedDocument {
        const copy: RelatedDocument = Object.assign({}, relatedDocument);
        return copy;
    }
}
