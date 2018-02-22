/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { VipAdpqTestModule } from '../../../test.module';
import { RelatedDocumentDetailComponent } from '../../../../../../main/webapp/app/entities/related-document/related-document-detail.component';
import { RelatedDocumentService } from '../../../../../../main/webapp/app/entities/related-document/related-document.service';
import { RelatedDocument } from '../../../../../../main/webapp/app/entities/related-document/related-document.model';

describe('Component Tests', () => {

    describe('RelatedDocument Management Detail Component', () => {
        let comp: RelatedDocumentDetailComponent;
        let fixture: ComponentFixture<RelatedDocumentDetailComponent>;
        let service: RelatedDocumentService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [VipAdpqTestModule],
                declarations: [RelatedDocumentDetailComponent],
                providers: [
                    RelatedDocumentService
                ]
            })
            .overrideTemplate(RelatedDocumentDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelatedDocumentDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelatedDocumentService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new RelatedDocument(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.relatedDocument).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
