import { Injectable } from '@angular/core';
import { Http, Response, URLSearchParams, BaseRequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Project } from './project.model';
import { DateUtils } from 'ng-jhipster';
@Injectable()
export class ProjectService {

    private resourceUrl = 'api/projects';

    constructor(private http: Http, private dateUtils: DateUtils) { }

    create(project: Project): Observable<Project> {
        const copy: Project = Object.assign({}, project);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(project: Project): Observable<Project> {
        const copy: Project = Object.assign({}, project);
        copy.created_date = null;
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<Project> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            jsonResponse.created_date = this.dateUtils
                .convertLocalDateFromServer(jsonResponse.created_date);
            return jsonResponse;
        });
    }

    cases(id: number): Observable<Response> {
        return this.http.get(`${this.resourceUrl}/${id}/test-cases`)
            .map((res: any) => this.convertResponse(res));
    }

    query(req?: any): Observable<Response> {
        const options = this.createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: any) => this.convertResponse(res))
        ;
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: any): any {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            jsonResponse[i].created_date = this.dateUtils
                .convertLocalDateFromServer(jsonResponse[i].created_date);
        }
        res._body = jsonResponse;
        return res;
    }

    private createRequestOption(req?: any): BaseRequestOptions {
        const options: BaseRequestOptions = new BaseRequestOptions();
        if (req) {
            const params: URLSearchParams = new URLSearchParams();
            params.set('page', req.page);
            params.set('size', req.size);
            if (req.sort) {
                params.paramsMap.set('sort', req.sort);
            }
            params.set('query', req.query);

            options.search = params;
        }
        return options;
    }
}
