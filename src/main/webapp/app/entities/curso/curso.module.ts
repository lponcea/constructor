import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { CursoDetailComponent } from './curso-detail.component';
import { CursoDeleteDialogComponent } from './curso-delete-dialog.component';
import { cursoRoute } from './curso.route';
import { CursoComponent } from './curso.component';

@NgModule({
  imports: [ConstructorSharedModule, RouterModule.forChild(cursoRoute)],
  declarations: [CursoDetailComponent, CursoDeleteDialogComponent, CursoComponent],
  entryComponents: [CursoDeleteDialogComponent]
})
export class ConstructorCursoModule {}
