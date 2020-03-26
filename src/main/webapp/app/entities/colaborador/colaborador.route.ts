import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IColaborador, Colaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from './colaborador.service';
import { ColaboradorComponent } from './colaborador.component';
import { ColaboradorDetailComponent } from './colaborador-detail.component';
import { ColaboradorUpdateComponent } from './colaborador-update.component';

@Injectable({ providedIn: 'root' })
export class ColaboradorResolve implements Resolve<IColaborador> {
  constructor(private service: ColaboradorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IColaborador> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((colaborador: HttpResponse<Colaborador>) => {
          if (colaborador.body) {
            return of(colaborador.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Colaborador());
  }
}

export const colaboradorRoute: Routes = [
  {
    path: '',
    component: ColaboradorComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'constructorApp.colaborador.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ColaboradorDetailComponent,
    resolve: {
      colaborador: ColaboradorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.colaborador.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ColaboradorUpdateComponent,
    resolve: {
      colaborador: ColaboradorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.colaborador.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ColaboradorUpdateComponent,
    resolve: {
      colaborador: ColaboradorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.colaborador.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
