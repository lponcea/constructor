import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { FichaComponent } from './ficha.component';
import { FichaDetailComponent } from './ficha-detail.component';
import { FichaUpdateComponent } from './ficha-update.component';
import { FichaDeleteDialogComponent } from './ficha-delete-dialog.component';
import { fichaRoute } from './ficha.route';

@NgModule({
  imports: [ConstructorSharedModule, RouterModule.forChild(fichaRoute)],
  declarations: [FichaComponent, FichaDetailComponent, FichaUpdateComponent, FichaDeleteDialogComponent],
  entryComponents: [FichaDeleteDialogComponent]
})
export class ConstructorFichaModule {}
