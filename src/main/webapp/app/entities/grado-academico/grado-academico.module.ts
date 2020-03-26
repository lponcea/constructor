import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { GradoAcademicoComponent } from './grado-academico.component';
import { GradoAcademicoDetailComponent } from './grado-academico-detail.component';
import { GradoAcademicoUpdateComponent } from './grado-academico-update.component';
import { GradoAcademicoDeleteDialogComponent } from './grado-academico-delete-dialog.component';
import { gradoAcademicoRoute } from './grado-academico.route';

@NgModule({
  imports: [ConstructorSharedModule, RouterModule.forChild(gradoAcademicoRoute)],
  declarations: [
    GradoAcademicoComponent,
    GradoAcademicoDetailComponent,
    GradoAcademicoUpdateComponent,
    GradoAcademicoDeleteDialogComponent
  ],
  entryComponents: [GradoAcademicoDeleteDialogComponent]
})
export class ConstructorGradoAcademicoModule {}
