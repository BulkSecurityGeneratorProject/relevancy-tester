import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Response} from '@angular/http';

import {NgbActiveModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {EventManager, AlertService, JhiLanguageService} from 'ng-jhipster';

import {TestCase} from './test-case.model';
import {TestCasePopupService} from './test-case-popup.service';
import {TestCaseService} from './test-case.service';
import {Project, ProjectService} from '../project';

@Component({
    selector: 'jhi-test-case-dialog',
    templateUrl: './test-case-dialog.component.html'
})
export class TestCaseDialogComponent implements OnInit {

    testCase: TestCase;
    authorities: any[];
    isSaving: boolean;
    project: Project;

    constructor(public activeModal: NgbActiveModal,
                private jhiLanguageService: JhiLanguageService,
                private alertService: AlertService,
                private testCaseService: TestCaseService,
                private eventManager: EventManager) {
        this.jhiLanguageService.setLocations(['testCase', 'project']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        this.testCase.projectId = this.project.id;
        if (this.testCase.id !== undefined) {
            this.testCaseService.update(this.testCase)
                .subscribe((res: TestCase) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.testCaseService.create(this.testCase)
                .subscribe((res: TestCase) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: TestCase) {
        this.eventManager.broadcast({name: 'testCaseListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackProjectById(index: number, item: Project) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-test-case-popup',
    template: ''
})
export class TestCasePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(private route: ActivatedRoute,
                private testCasePopupService: TestCasePopupService) {
    }

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if (params['id']) {
                this.modalRef = this.testCasePopupService
                    .open(TestCaseDialogComponent, params['projectId'], params['id']);
            } else {
                this.modalRef = this.testCasePopupService
                    .open(TestCaseDialogComponent, params['projectId']);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
