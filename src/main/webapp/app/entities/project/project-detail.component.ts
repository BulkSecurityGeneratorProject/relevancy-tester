import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import {EventManager, JhiLanguageService, AlertService} from 'ng-jhipster';

import { Project } from './project.model';
import { ProjectService } from './project.service';
import {TestCase} from "../test-case/test-case.model";
import {Response} from "@angular/http";

@Component({
    selector: 'jhi-project-detail',
    templateUrl: './project-detail.component.html'
})
export class ProjectDetailComponent implements OnInit, OnDestroy {

    project: Project;
    cases: TestCase[];
    private subscription: any;
    private eventProjectSubscriber: Subscription;
    private eventCaseSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private projectService: ProjectService,
        private route: ActivatedRoute,
        private alertService: AlertService
    ) {
        this.jhiLanguageService.setLocations(['project']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProjects();
    }

    load(id) {
        this.projectService.find(id).subscribe((project) => {
            this.project = project;
        });
        this.projectService.cases(id).subscribe(
            (res: Response) => {
                this.cases = res.json();
            },
            (res: Response) => this.onError(res.json())
        );
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventProjectSubscriber);
        this.eventManager.destroy(this.eventCaseSubscriber);
    }

    registerChangeInProjects() {
        this.eventProjectSubscriber = this.eventManager.subscribe('projectListModification', (response) => this.load(this.project.id));
        this.eventCaseSubscriber = this.eventManager.subscribe('testCaseListModification', (response) => this.load(this.project.id));
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
