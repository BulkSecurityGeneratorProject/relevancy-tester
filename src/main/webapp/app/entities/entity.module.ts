import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { RelevancyTesterProjectModule } from './project/project.module';
import { RelevancyTesterTestCaseModule } from './test-case/test-case.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        RelevancyTesterProjectModule,
        RelevancyTesterTestCaseModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RelevancyTesterEntityModule {}
