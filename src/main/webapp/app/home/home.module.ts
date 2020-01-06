import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { ConstructorComponentTypesComponent } from './../constructor/constructor-component-types/constructor-component-types.component';
import { ConstructorFilmstripComponent } from './../constructor/constructor-filmstrip/constructor-filmstrip.component';
import { ConstructorComponentSubtypesComponent } from './../constructor/constructor-component-subtypes/constructor-component-subtypes.component';
import { ConstructorVisorContainerComponent } from './../constructor/constructor-visor-container/constructor-visor-container.component';
import { ConstructorComponentContainerComponent } from './../constructor/constructor-component-container/constructor-component-container.component';
import { ConstructorTextComponent } from './../constructor/components/constructor-text/constructor-text.component';
import { VisorTextComponent } from './../visor/components/visor-text/visor-text.component';

@NgModule({
  imports: [ConstructorSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [
    HomeComponent,
    ConstructorComponentTypesComponent,
    ConstructorFilmstripComponent,
    ConstructorComponentSubtypesComponent,
    ConstructorVisorContainerComponent,
    ConstructorComponentContainerComponent,
    ConstructorTextComponent,
    VisorTextComponent
  ]
})
export class ConstructorHomeModule {}
