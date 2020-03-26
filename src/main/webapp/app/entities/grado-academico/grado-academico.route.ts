import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGradoAcademico, GradoAcademico } from 'app/shared/model/grado-academico.model';
import { GradoAcademicoService } from './grado-academico.service';
import { GradoAcademicoComponent } from './grado-academico.component';
import { GradoAcademicoDetailComponent } from './grado-academico-detail.component';
import { GradoAcademicoUpdateComponent } from './grado-academico-update.component';

@Injectable({ providedIn: 'root' })
export class GradoAcademicoResolve implements Resolve<IGradoAcademico> {
  constructor(private service: GradoAcademicoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGradoAcademico> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((gradoAcademico: HttpResponse<GradoAcademico>) => {
          if (gradoAcademico.body) {
            return of(gradoAcademico.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GradoAcademico());
  }
}

export const gradoAcademicoRoute: Routes = [
  {
    path: '',
    component: GradoAcademicoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'constructorApp.gradoAcademico.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GradoAcademicoDetailComponent,
    resolve: {
      gradoAcademico: GradoAcademicoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.gradoAcademico.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GradoAcademicoUpdateComponent,
    resolve: {
      gradoAcademico: GradoAcademicoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.gradoAcademico.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GradoAcademicoUpdateComponent,
    resolve: {
      gradoAcademico: GradoAcademicoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.gradoAcademico.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
