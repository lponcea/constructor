import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAsignatura, Asignatura } from 'app/shared/model/asignatura.model';
import { AsignaturaService } from './asignatura.service';
import { AsignaturaComponent } from './asignatura.component';
import { AsignaturaDetailComponent } from './asignatura-detail.component';
import { AsignaturaUpdateComponent } from './asignatura-update.component';

@Injectable({ providedIn: 'root' })
export class AsignaturaResolve implements Resolve<IAsignatura> {
  constructor(private service: AsignaturaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAsignatura> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((asignatura: HttpResponse<Asignatura>) => {
          if (asignatura.body) {
            return of(asignatura.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Asignatura());
  }
}

export const asignaturaRoute: Routes = [
  {
    path: '',
    component: AsignaturaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'constructorApp.asignatura.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AsignaturaDetailComponent,
    resolve: {
      asignatura: AsignaturaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.asignatura.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AsignaturaUpdateComponent,
    resolve: {
      asignatura: AsignaturaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.asignatura.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AsignaturaUpdateComponent,
    resolve: {
      asignatura: AsignaturaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.asignatura.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
