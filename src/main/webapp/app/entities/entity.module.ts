import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplicationProductModule } from './product/product.module';
import { JhipsterSampleApplicationProductCategoryModule } from './product-category/product-category.module';
import { JhipsterSampleApplicationCustomerModule } from './customer/customer.module';
import { JhipsterSampleApplicationProductOrderModule } from './product-order/product-order.module';
import { JhipsterSampleApplicationOrderItemModule } from './order-item/order-item.module';
import { JhipsterSampleApplicationInvoiceModule } from './invoice/invoice.module';
import { JhipsterSampleApplicationShipmentModule } from './shipment/shipment.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JhipsterSampleApplicationProductModule,
        JhipsterSampleApplicationProductCategoryModule,
        JhipsterSampleApplicationCustomerModule,
        JhipsterSampleApplicationProductOrderModule,
        JhipsterSampleApplicationOrderItemModule,
        JhipsterSampleApplicationInvoiceModule,
        JhipsterSampleApplicationShipmentModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}
