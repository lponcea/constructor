import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRolColaborador, RolColaborador } from 'app/shared/model/rol-colaborador.model';
import { RolColaboradorService } from './rol-colaborador.service';
import { RolColaboradorComponent } from './rol-colaborador.component';
import { RolColaboradorDetailComponent } from './rol-colaborador-detail.component';
import { RolColaboradorUpdateComponent } from './rol-colaborador-update.component';

@Injectable({ providedIn: 'root' })
export class RolColaboradorResolve implements Resolve<IRolColaborador> {
  constructor(private service: RolColaboradorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRolColaborador> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rolColaborador: HttpResponse<RolColaborador>) => {
          if (rolColaborador.body) {
            return of(rolColaborador.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RolColaborador());
  }
}

export const rolColaboradorRoute: Routes = [
  {
    path: '',
    component: RolColaboradorComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'constructorApp.rolColaborador.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RolColaboradorDetailComponent,
    resolve: {
      rolColaborador: RolColaboradorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.rolColaborador.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RolColaboradorUpdateComponent,
    resolve: {
      rolColaborador: RolColaboradorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.rolColaborador.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RolColaboradorUpdateComponent,
    resolve: {
      rolColaborador: RolColaboradorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.rolColaborador.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
