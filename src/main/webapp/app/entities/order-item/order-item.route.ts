import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { OrderItemComponent } from './order-item.component';
import { OrderItemDetailComponent } from './order-item-detail.component';
import { OrderItemPopupComponent } from './order-item-dialog.component';
import { OrderItemDeletePopupComponent } from './order-item-delete-dialog.component';

export const orderItemRoute: Routes = [
    {
        path: 'order-item',
        component: OrderItemComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.orderItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'order-item/:id',
        component: OrderItemDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.orderItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const orderItemPopupRoute: Routes = [
    {
        path: 'order-item-new',
        component: OrderItemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.orderItem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'order-item/:id/edit',
        component: OrderItemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.orderItem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'order-item/:id/delete',
        component: OrderItemDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.orderItem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
