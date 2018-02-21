/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { VipAdpqTestModule } from '../../../test.module';
import { TaskOwnerDetailComponent } from '../../../../../../main/webapp/app/entities/task-owner/task-owner-detail.component';
import { TaskOwnerService } from '../../../../../../main/webapp/app/entities/task-owner/task-owner.service';
import { TaskOwner } from '../../../../../../main/webapp/app/entities/task-owner/task-owner.model';

describe('Component Tests', () => {

    describe('TaskOwner Management Detail Component', () => {
        let comp: TaskOwnerDetailComponent;
        let fixture: ComponentFixture<TaskOwnerDetailComponent>;
        let service: TaskOwnerService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [VipAdpqTestModule],
                declarations: [TaskOwnerDetailComponent],
                providers: [
                    TaskOwnerService
                ]
            })
            .overrideTemplate(TaskOwnerDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TaskOwnerDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TaskOwnerService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TaskOwner(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.taskOwner).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
