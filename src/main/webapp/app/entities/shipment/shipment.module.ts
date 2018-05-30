import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    ShipmentService,
    ShipmentPopupService,
    ShipmentComponent,
    ShipmentDetailComponent,
    ShipmentDialogComponent,
    ShipmentPopupComponent,
    ShipmentDeletePopupComponent,
    ShipmentDeleteDialogComponent,
    shipmentRoute,
    shipmentPopupRoute,
} from './';

const ENTITY_STATES = [
    ...shipmentRoute,
    ...shipmentPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ShipmentComponent,
        ShipmentDetailComponent,
        ShipmentDialogComponent,
        ShipmentDeleteDialogComponent,
        ShipmentPopupComponent,
        ShipmentDeletePopupComponent,
    ],
    entryComponents: [
        ShipmentComponent,
        ShipmentDialogComponent,
        ShipmentPopupComponent,
        ShipmentDeleteDialogComponent,
        ShipmentDeletePopupComponent,
    ],
    providers: [
        ShipmentService,
        ShipmentPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationShipmentModule {}
