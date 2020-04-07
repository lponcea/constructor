import { Route } from '@angular/router';

import { HomeComponent } from './home.component';
import { AuthGuardService } from 'app/services/auth-guard-service.service';

export const HOME_ROUTE: Route = {
  path: '',
  component: HomeComponent,
  canActivate: [AuthGuardService],
  data: {
    authorities: [],
    pageTitle: 'home.title'
  }
};
