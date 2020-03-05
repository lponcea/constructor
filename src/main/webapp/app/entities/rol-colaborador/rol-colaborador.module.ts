import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { RolColaboradorComponent } from './rol-colaborador.component';
import { RolColaboradorDetailComponent } from './rol-colaborador-detail.component';
import { RolColaboradorUpdateComponent } from './rol-colaborador-update.component';
import { RolColaboradorDeleteDialogComponent } from './rol-colaborador-delete-dialog.component';
import { rolColaboradorRoute } from './rol-colaborador.route';

@NgModule({
  imports: [ConstructorSharedModule, RouterModule.forChild(rolColaboradorRoute)],
  declarations: [
    RolColaboradorComponent,
    RolColaboradorDetailComponent,
    RolColaboradorUpdateComponent,
    RolColaboradorDeleteDialogComponent
  ],
  entryComponents: [RolColaboradorDeleteDialogComponent]
})
export class ConstructorRolColaboradorModule {}
