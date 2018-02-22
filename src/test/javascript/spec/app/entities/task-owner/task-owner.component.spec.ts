/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { VipAdpqTestModule } from '../../../test.module';
import { TaskOwnerComponent } from '../../../../../../main/webapp/app/entities/task-owner/task-owner.component';
import { TaskOwnerService } from '../../../../../../main/webapp/app/entities/task-owner/task-owner.service';
import { TaskOwner } from '../../../../../../main/webapp/app/entities/task-owner/task-owner.model';

describe('Component Tests', () => {

    describe('TaskOwner Management Component', () => {
        let comp: TaskOwnerComponent;
        let fixture: ComponentFixture<TaskOwnerComponent>;
        let service: TaskOwnerService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [VipAdpqTestModule],
                declarations: [TaskOwnerComponent],
                providers: [
                    TaskOwnerService
                ]
            })
            .overrideTemplate(TaskOwnerComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TaskOwnerComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TaskOwnerService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TaskOwner(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.taskOwners[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
