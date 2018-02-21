/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { VipAdpqTestModule } from '../../../test.module';
import { TaskOwnerDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/task-owner/task-owner-delete-dialog.component';
import { TaskOwnerService } from '../../../../../../main/webapp/app/entities/task-owner/task-owner.service';

describe('Component Tests', () => {

    describe('TaskOwner Management Delete Component', () => {
        let comp: TaskOwnerDeleteDialogComponent;
        let fixture: ComponentFixture<TaskOwnerDeleteDialogComponent>;
        let service: TaskOwnerService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [VipAdpqTestModule],
                declarations: [TaskOwnerDeleteDialogComponent],
                providers: [
                    TaskOwnerService
                ]
            })
            .overrideTemplate(TaskOwnerDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TaskOwnerDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TaskOwnerService);
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
