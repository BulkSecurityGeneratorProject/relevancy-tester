import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { TestCase } from './test-case.model';
import { TestCasePopupService } from './test-case-popup.service';
import { TestCaseService } from './test-case.service';

@Component({
    selector: 'jhi-test-case-delete-dialog',
    templateUrl: './test-case-delete-dialog.component.html'
})
export class TestCaseDeleteDialogComponent {

    testCase: TestCase;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private testCaseService: TestCaseService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['testCase','project']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.testCaseService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'testCaseListModification',
                content: 'Deleted an testCase'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-test-case-delete-popup',
    template: ''
})
export class TestCaseDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private testCasePopupService: TestCasePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.testCasePopupService
                .open(TestCaseDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
