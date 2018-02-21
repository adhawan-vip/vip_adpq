/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { VipAdpqTestModule } from '../../../test.module';
import { RelatedDocumentDialogComponent } from '../../../../../../main/webapp/app/entities/related-document/related-document-dialog.component';
import { RelatedDocumentService } from '../../../../../../main/webapp/app/entities/related-document/related-document.service';
import { RelatedDocument } from '../../../../../../main/webapp/app/entities/related-document/related-document.model';
import { ArticleService } from '../../../../../../main/webapp/app/entities/article';

describe('Component Tests', () => {

    describe('RelatedDocument Management Dialog Component', () => {
        let comp: RelatedDocumentDialogComponent;
        let fixture: ComponentFixture<RelatedDocumentDialogComponent>;
        let service: RelatedDocumentService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [VipAdpqTestModule],
                declarations: [RelatedDocumentDialogComponent],
                providers: [
                    ArticleService,
                    RelatedDocumentService
                ]
            })
            .overrideTemplate(RelatedDocumentDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelatedDocumentDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelatedDocumentService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new RelatedDocument(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.relatedDocument = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'relatedDocumentListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new RelatedDocument();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.relatedDocument = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'relatedDocumentListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
