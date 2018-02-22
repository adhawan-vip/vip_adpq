/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { VipAdpqTestModule } from '../../../test.module';
import { RelatedDocumentComponent } from '../../../../../../main/webapp/app/entities/related-document/related-document.component';
import { RelatedDocumentService } from '../../../../../../main/webapp/app/entities/related-document/related-document.service';
import { RelatedDocument } from '../../../../../../main/webapp/app/entities/related-document/related-document.model';

describe('Component Tests', () => {

    describe('RelatedDocument Management Component', () => {
        let comp: RelatedDocumentComponent;
        let fixture: ComponentFixture<RelatedDocumentComponent>;
        let service: RelatedDocumentService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [VipAdpqTestModule],
                declarations: [RelatedDocumentComponent],
                providers: [
                    RelatedDocumentService
                ]
            })
            .overrideTemplate(RelatedDocumentComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelatedDocumentComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelatedDocumentService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new RelatedDocument(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.relatedDocuments[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
