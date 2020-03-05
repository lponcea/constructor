import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { NumeroGradoComponent } from './numero-grado.component';
import { NumeroGradoDetailComponent } from './numero-grado-detail.component';
import { NumeroGradoUpdateComponent } from './numero-grado-update.component';
import { NumeroGradoDeleteDialogComponent } from './numero-grado-delete-dialog.component';
import { numeroGradoRoute } from './numero-grado.route';

@NgModule({
  imports: [ConstructorSharedModule, RouterModule.forChild(numeroGradoRoute)],
  declarations: [NumeroGradoComponent, NumeroGradoDetailComponent, NumeroGradoUpdateComponent, NumeroGradoDeleteDialogComponent],
  entryComponents: [NumeroGradoDeleteDialogComponent]
})
export class ConstructorNumeroGradoModule {}
