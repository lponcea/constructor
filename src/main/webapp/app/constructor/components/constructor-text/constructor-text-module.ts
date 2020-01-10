import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AngularEditorModule } from '@kolkov/angular-editor';

@NgModule({
  imports: [HttpClientModule],
  declarations: [AngularEditorModule]
})
export class ConstructorTextModule {}
