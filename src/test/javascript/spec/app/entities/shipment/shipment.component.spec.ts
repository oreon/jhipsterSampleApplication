/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ShipmentComponent } from '../../../../../../main/webapp/app/entities/shipment/shipment.component';
import { ShipmentService } from '../../../../../../main/webapp/app/entities/shipment/shipment.service';
import { Shipment } from '../../../../../../main/webapp/app/entities/shipment/shipment.model';

describe('Component Tests', () => {

    describe('Shipment Management Component', () => {
        let comp: ShipmentComponent;
        let fixture: ComponentFixture<ShipmentComponent>;
        let service: ShipmentService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ShipmentComponent],
                providers: [
                    ShipmentService
                ]
            })
            .overrideTemplate(ShipmentComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ShipmentComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ShipmentService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Shipment(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.shipments[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
