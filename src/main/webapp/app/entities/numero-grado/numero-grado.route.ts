import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INumeroGrado, NumeroGrado } from 'app/shared/model/numero-grado.model';
import { NumeroGradoService } from './numero-grado.service';
import { NumeroGradoComponent } from './numero-grado.component';
import { NumeroGradoDetailComponent } from './numero-grado-detail.component';
import { NumeroGradoUpdateComponent } from './numero-grado-update.component';

@Injectable({ providedIn: 'root' })
export class NumeroGradoResolve implements Resolve<INumeroGrado> {
  constructor(private service: NumeroGradoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INumeroGrado> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((numeroGrado: HttpResponse<NumeroGrado>) => {
          if (numeroGrado.body) {
            return of(numeroGrado.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NumeroGrado());
  }
}

export const numeroGradoRoute: Routes = [
  {
    path: '',
    component: NumeroGradoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'constructorApp.numeroGrado.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NumeroGradoDetailComponent,
    resolve: {
      numeroGrado: NumeroGradoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.numeroGrado.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NumeroGradoUpdateComponent,
    resolve: {
      numeroGrado: NumeroGradoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.numeroGrado.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NumeroGradoUpdateComponent,
    resolve: {
      numeroGrado: NumeroGradoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.numeroGrado.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
