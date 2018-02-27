import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Article } from './article.model';
import { ArticleService } from './article.service';

@Injectable()
export class ArticlePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private articleService: ArticleService

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
                this.articleService.find(id)
                    .subscribe((articleResponse: HttpResponse<Article>) => {
                        const article: Article = articleResponse.body;
                        if (article.publishDate) {
                            article.publishDate = {
                                year: article.publishDate.getFullYear(),
                                month: article.publishDate.getMonth() + 1,
                                day: article.publishDate.getDate()
                            };
                        }
                        if (article.createdOn) {
                            article.createdOn = {
                                year: article.createdOn.getFullYear(),
                                month: article.createdOn.getMonth() + 1,
                                day: article.createdOn.getDate()
                            };
                        }
                        if (article.modifiedOn) {
                            article.modifiedOn = {
                                year: article.modifiedOn.getFullYear(),
                                month: article.modifiedOn.getMonth() + 1,
                                day: article.modifiedOn.getDate()
                            };
                        }
                        this.ngbModalRef = this.articleModalRef(component, article);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.articleModalRef(component, new Article());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    articleModalRef(component: Component, article: Article): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.article = article;
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
