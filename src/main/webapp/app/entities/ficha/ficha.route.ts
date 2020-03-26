import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFicha, Ficha } from 'app/shared/model/ficha.model';
import { FichaService } from './ficha.service';
import { FichaComponent } from './ficha.component';
import { FichaDetailComponent } from './ficha-detail.component';
import { FichaUpdateComponent } from './ficha-update.component';

@Injectable({ providedIn: 'root' })
export class FichaResolve implements Resolve<IFicha> {
  constructor(private service: FichaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFicha> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ficha: HttpResponse<Ficha>) => {
          if (ficha.body) {
            return of(ficha.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Ficha());
  }
}

export const fichaRoute: Routes = [
  {
    path: '',
    component: FichaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'constructorApp.ficha.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FichaDetailComponent,
    resolve: {
      ficha: FichaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.ficha.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FichaUpdateComponent,
    resolve: {
      ficha: FichaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.ficha.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FichaUpdateComponent,
    resolve: {
      ficha: FichaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'constructorApp.ficha.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
