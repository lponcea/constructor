import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { errorRoute } from './layouts/error/error.route';
import { navbarRoute } from './layouts/navbar/navbar.route';
import { DEBUG_INFO_ENABLED } from 'app/app.constants';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ConstructorLayoutComponent } from './constructor-layout/constructor-layout.component';
import { CourseConfigurationComponent } from './course-configuration/course-configuration.component';
import { HomePageComponent } from './home-page/home-page.component';

const LAYOUT_ROUTES = [navbarRoute, ...errorRoute];

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        {
          path: 'admin',
          data: {
            authorities: ['ROLE_ADMIN']
          },
          canActivate: [UserRouteAccessService],
          loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule)
        },
        {
          path: 'account',
          loadChildren: () => import('./account/account.module').then(m => m.AccountModule)
        },
        {
          path: 'constructor-layout/:cursoId',
          component: ConstructorLayoutComponent
        },
        {
          path: 'home-page',
          component: HomePageComponent
        },
        {
          path: 'course-configuration',
          component: CourseConfigurationComponent
        },
        ...LAYOUT_ROUTES
      ],
      { enableTracing: DEBUG_INFO_ENABLED }
    )
  ],
  exports: [RouterModule]
})
export class ConstructorAppRoutingModule {}
