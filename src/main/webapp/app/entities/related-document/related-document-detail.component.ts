import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { RelatedDocument } from './related-document.model';
import { RelatedDocumentService } from './related-document.service';

@Component({
    selector: 'jhi-related-document-detail',
    templateUrl: './related-document-detail.component.html'
})
export class RelatedDocumentDetailComponent implements OnInit, OnDestroy {

    relatedDocument: RelatedDocument;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private relatedDocumentService: RelatedDocumentService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRelatedDocuments();
    }

    load(id) {
        this.relatedDocumentService.find(id)
            .subscribe((relatedDocumentResponse: HttpResponse<RelatedDocument>) => {
                this.relatedDocument = relatedDocumentResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRelatedDocuments() {
        this.eventSubscriber = this.eventManager.subscribe(
            'relatedDocumentListModification',
            (response) => this.load(this.relatedDocument.id)
        );
    }
}
