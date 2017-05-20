import {Injectable, Component} from '@angular/core';
import {Router} from '@angular/router';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {DatePipe} from '@angular/common';
import {TestCase} from './test-case.model';
import {TestCaseService} from './test-case.service';
import {ProjectService} from "../project/project.service";
import {Project} from "../project/project.model";
@Injectable()
export class TestCasePopupService {
    private isOpen = false;

    constructor(private datePipe: DatePipe,
                private modalService: NgbModal,
                private router: Router,
                private testCaseService: TestCaseService,
                private projectService: ProjectService
    ) {
    }

    open(component: Component, projectId: number, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;
        this.projectService.find(projectId).subscribe((project) => {
            if (id) {
                this.testCaseService.find(id).subscribe((testCase) => {
                    testCase.createdDate = this.datePipe
                        .transform(testCase.createdDate, 'yyyy-MM-ddThh:mm');
                    this.testCaseModalRef(component, project, testCase);
                });
            } else {
                return this.testCaseModalRef(component, project, new TestCase());
            }
        });
    }

    testCaseModalRef(component: Component, project:Project, testCase: TestCase): NgbModalRef {
        const modalRef = this.modalService.open(component, {size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.testCase = testCase;
        modalRef.componentInstance.project = project;
        modalRef.result.then((result) => {
            this.router.navigate([{outlets: {popup: null}}], {replaceUrl: true});
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{outlets: {popup: null}}], {replaceUrl: true});
            this.isOpen = false;
        });
        return modalRef;
    }
}
