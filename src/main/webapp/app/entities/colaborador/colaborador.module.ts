import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { ColaboradorComponent } from './colaborador.component';
import { ColaboradorDetailComponent } from './colaborador-detail.component';
import { ColaboradorUpdateComponent } from './colaborador-update.component';
import { ColaboradorDeleteDialogComponent } from './colaborador-delete-dialog.component';
import { colaboradorRoute } from './colaborador.route';

@NgModule({
  imports: [ConstructorSharedModule, RouterModule.forChild(colaboradorRoute)],
  declarations: [ColaboradorComponent, ColaboradorDetailComponent, ColaboradorUpdateComponent, ColaboradorDeleteDialogComponent],
  entryComponents: [ColaboradorDeleteDialogComponent]
})
export class ConstructorColaboradorModule {}
