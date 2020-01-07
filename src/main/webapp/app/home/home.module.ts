import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { ConstructorFilmstripComponent } from './../constructor/constructor-filmstrip/constructor-filmstrip.component';
import { ConstructorVisorContainerComponent } from './../constructor/constructor-visor-container/constructor-visor-container.component';
import { ConstructorComponentContainerComponent } from './../constructor/constructor-component-container/constructor-component-container.component';
import { ConstructorTextComponent } from './../constructor/components/constructor-text/constructor-text.component';
import { VisorTextComponent } from './../visor/components/visor-text/visor-text.component';
import { LeftSidebarComponent } from './../layouts/left-sidebar/left-sidebar.component';
import { RightSidebarComponent } from './../layouts/right-sidebar/right-sidebar.component';

@NgModule({
  imports: [ConstructorSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [
    HomeComponent,
    ConstructorFilmstripComponent,
    ConstructorVisorContainerComponent,
    ConstructorComponentContainerComponent,
    ConstructorTextComponent,
    VisorTextComponent,
    LeftSidebarComponent,
    RightSidebarComponent
  ]
})
export class ConstructorHomeModule {}
