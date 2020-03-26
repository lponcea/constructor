import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { AsignaturaComponent } from './asignatura.component';
import { AsignaturaDetailComponent } from './asignatura-detail.component';
import { AsignaturaUpdateComponent } from './asignatura-update.component';
import { AsignaturaDeleteDialogComponent } from './asignatura-delete-dialog.component';
import { asignaturaRoute } from './asignatura.route';

@NgModule({
  imports: [ConstructorSharedModule, RouterModule.forChild(asignaturaRoute)],
  declarations: [AsignaturaComponent, AsignaturaDetailComponent, AsignaturaUpdateComponent, AsignaturaDeleteDialogComponent],
  entryComponents: [AsignaturaDeleteDialogComponent]
})
export class ConstructorAsignaturaModule {}
