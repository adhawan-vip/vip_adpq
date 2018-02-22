/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { VipAdpqTestModule } from '../../../test.module';
import { RelatedDocumentDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/related-document/related-document-delete-dialog.component';
import { RelatedDocumentService } from '../../../../../../main/webapp/app/entities/related-document/related-document.service';

describe('Component Tests', () => {

    describe('RelatedDocument Management Delete Component', () => {
        let comp: RelatedDocumentDeleteDialogComponent;
        let fixture: ComponentFixture<RelatedDocumentDeleteDialogComponent>;
        let service: RelatedDocumentService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [VipAdpqTestModule],
                declarations: [RelatedDocumentDeleteDialogComponent],
                providers: [
                    RelatedDocumentService
                ]
            })
            .overrideTemplate(RelatedDocumentDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelatedDocumentDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelatedDocumentService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
