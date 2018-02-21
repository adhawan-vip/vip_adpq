import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { RelatedDocument } from './related-document.model';
import { RelatedDocumentService } from './related-document.service';

@Injectable()
export class RelatedDocumentPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private relatedDocumentService: RelatedDocumentService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.relatedDocumentService.find(id)
                    .subscribe((relatedDocumentResponse: HttpResponse<RelatedDocument>) => {
                        const relatedDocument: RelatedDocument = relatedDocumentResponse.body;
                        this.ngbModalRef = this.relatedDocumentModalRef(component, relatedDocument);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.relatedDocumentModalRef(component, new RelatedDocument());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    relatedDocumentModalRef(component: Component, relatedDocument: RelatedDocument): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.relatedDocument = relatedDocument;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
