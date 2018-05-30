import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    ProductCategoryService,
    ProductCategoryPopupService,
    ProductCategoryComponent,
    ProductCategoryDetailComponent,
    ProductCategoryDialogComponent,
    ProductCategoryPopupComponent,
    ProductCategoryDeletePopupComponent,
    ProductCategoryDeleteDialogComponent,
    productCategoryRoute,
    productCategoryPopupRoute,
} from './';

const ENTITY_STATES = [
    ...productCategoryRoute,
    ...productCategoryPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ProductCategoryComponent,
        ProductCategoryDetailComponent,
        ProductCategoryDialogComponent,
        ProductCategoryDeleteDialogComponent,
        ProductCategoryPopupComponent,
        ProductCategoryDeletePopupComponent,
    ],
    entryComponents: [
        ProductCategoryComponent,
        ProductCategoryDialogComponent,
        ProductCategoryPopupComponent,
        ProductCategoryDeleteDialogComponent,
        ProductCategoryDeletePopupComponent,
    ],
    providers: [
        ProductCategoryService,
        ProductCategoryPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationProductCategoryModule {}
