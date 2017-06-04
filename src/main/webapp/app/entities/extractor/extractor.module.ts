import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {ExtractorService} from "./extractor.service";


@NgModule({
    providers: [
        ExtractorService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RelevancyTesterExtractorModule {}
