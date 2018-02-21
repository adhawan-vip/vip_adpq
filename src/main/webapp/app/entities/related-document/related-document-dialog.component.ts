import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { RelatedDocument } from './related-document.model';
import { RelatedDocumentPopupService } from './related-document-popup.service';
import { RelatedDocumentService } from './related-document.service';
import { Article, ArticleService } from '../article';

@Component({
    selector: 'jhi-related-document-dialog',
    templateUrl: './related-document-dialog.component.html'
})
export class RelatedDocumentDialogComponent implements OnInit {

    relatedDocument: RelatedDocument;
    isSaving: boolean;

    articles: Article[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private relatedDocumentService: RelatedDocumentService,
        private articleService: ArticleService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.articleService.query()
            .subscribe((res: HttpResponse<Article[]>) => { this.articles = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.relatedDocument.id !== undefined) {
            this.subscribeToSaveResponse(
                this.relatedDocumentService.update(this.relatedDocument));
        } else {
            this.subscribeToSaveResponse(
                this.relatedDocumentService.create(this.relatedDocument));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<RelatedDocument>>) {
        result.subscribe((res: HttpResponse<RelatedDocument>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: RelatedDocument) {
        this.eventManager.broadcast({ name: 'relatedDocumentListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackArticleById(index: number, item: Article) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-related-document-popup',
    template: ''
})
export class RelatedDocumentPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private relatedDocumentPopupService: RelatedDocumentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.relatedDocumentPopupService
                    .open(RelatedDocumentDialogComponent as Component, params['id']);
            } else {
                this.relatedDocumentPopupService
                    .open(RelatedDocumentDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
