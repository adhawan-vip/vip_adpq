import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RelatedDocument } from './related-document.model';
import { RelatedDocumentPopupService } from './related-document-popup.service';
import { RelatedDocumentService } from './related-document.service';

@Component({
    selector: 'jhi-related-document-delete-dialog',
    templateUrl: './related-document-delete-dialog.component.html'
})
export class RelatedDocumentDeleteDialogComponent {

    relatedDocument: RelatedDocument;

    constructor(
        private relatedDocumentService: RelatedDocumentService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.relatedDocumentService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'relatedDocumentListModification',
                content: 'Deleted an relatedDocument'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-related-document-delete-popup',
    template: ''
})
export class RelatedDocumentDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private relatedDocumentPopupService: RelatedDocumentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.relatedDocumentPopupService
                .open(RelatedDocumentDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
