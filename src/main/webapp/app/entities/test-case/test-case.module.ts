import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RelevancyTesterSharedModule } from '../../shared';
import {
    TestCaseService,
    TestCasePopupService,
    TestCaseDetailComponent,
    TestCaseDialogComponent,
    TestCasePopupComponent,
    TestCaseDeletePopupComponent,
    TestCaseDeleteDialogComponent,
    testCaseRoute,
    testCasePopupRoute,
} from './';

const ENTITY_STATES = [
    ...testCaseRoute,
    ...testCasePopupRoute,
];

@NgModule({
    imports: [
        RelevancyTesterSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TestCaseDetailComponent,
        TestCaseDialogComponent,
        TestCaseDeleteDialogComponent,
        TestCasePopupComponent,
        TestCaseDeletePopupComponent,
    ],
    entryComponents: [
        TestCaseDialogComponent,
        TestCasePopupComponent,
        TestCaseDeleteDialogComponent,
        TestCaseDeletePopupComponent,
    ],
    providers: [
        TestCaseService,
        TestCasePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RelevancyTesterTestCaseModule {}
