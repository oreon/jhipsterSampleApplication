import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ShipmentComponent } from './shipment.component';
import { ShipmentDetailComponent } from './shipment-detail.component';
import { ShipmentPopupComponent } from './shipment-dialog.component';
import { ShipmentDeletePopupComponent } from './shipment-delete-dialog.component';

export const shipmentRoute: Routes = [
    {
        path: 'shipment',
        component: ShipmentComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.shipment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'shipment/:id',
        component: ShipmentDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.shipment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const shipmentPopupRoute: Routes = [
    {
        path: 'shipment-new',
        component: ShipmentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.shipment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'shipment/:id/edit',
        component: ShipmentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.shipment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'shipment/:id/delete',
        component: ShipmentDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.shipment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
