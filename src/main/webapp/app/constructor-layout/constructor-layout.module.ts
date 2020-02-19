import { NgModule } from '@angular/core';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { ConstructorLayoutComponent } from './constructor-layout.component';
import { ConstructorFilmstripComponent } from './../constructor/constructor-filmstrip/constructor-filmstrip.component';
import { ConstructorVisorContainerComponent } from './../constructor/constructor-visor-container/constructor-visor-container.component';
import { ConstructorComponentContainerComponent } from './../constructor/constructor-component-container/constructor-component-container.component';
import { ConstructorTextComponent } from './../constructor/components/constructor-text/constructor-text.component';
import { ContentBlock1Component } from './../constructor/content-blocks/content-block1/content-block1.component';
import { VisorTextComponent } from './../visor/components/visor-text/visor-text.component';
import { TopBarComponent } from './../layouts/top-bar/top-bar.component';
import { LeftSidebarComponent } from './../layouts/left-sidebar/left-sidebar.component';
import { RightSidebarComponent } from './../layouts/right-sidebar/right-sidebar.component';
import { TemplateGalleryComponent } from '../constructor/template-gallery/template-gallery.component';
import { AngularEditorModule } from '@kolkov/angular-editor';

@NgModule({
  imports: [ConstructorSharedModule, AngularEditorModule],
  declarations: [
    ConstructorLayoutComponent,
    ConstructorFilmstripComponent,
    ConstructorVisorContainerComponent,
    ConstructorComponentContainerComponent,
    ConstructorTextComponent,
    ContentBlock1Component,
    VisorTextComponent,
    TopBarComponent,
    LeftSidebarComponent,
    RightSidebarComponent,
    TemplateGalleryComponent
  ]
})
export class ConstructorLayoutModule {}