import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { CursoComponent } from './curso.component';
import { CursoDetailComponent } from './curso-detail.component';
import { CursoUpdateComponent } from './curso-update.component';
import { CursoDeleteDialogComponent } from './curso-delete-dialog.component';
import { cursoRoute } from './curso.route';

@NgModule({
  imports: [ConstructorSharedModule, RouterModule.forChild(cursoRoute)],
  declarations: [CursoComponent, CursoDetailComponent, CursoUpdateComponent, CursoDeleteDialogComponent],
  entryComponents: [CursoDeleteDialogComponent]
})
export class ConstructorCursoModule {}
