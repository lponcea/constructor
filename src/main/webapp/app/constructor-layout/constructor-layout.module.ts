import { NgModule } from '@angular/core';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { ConstructorLayoutComponent } from './constructor-layout.component';
import { ConstructorFilmstripComponent } from './../constructor/constructor-filmstrip/constructor-filmstrip.component';
import { ConstructorVisorContainerComponent } from './../constructor/constructor-visor-container/constructor-visor-container.component';
import { ConstructorComponentContainerComponent } from './../constructor/constructor-component-container/constructor-component-container.component';
import { ConstructorTextComponent } from './../constructor/components/constructor-text/constructor-text.component';
import { ContentBlock3Component } from './../constructor/content-blocks/content-block3/content-block3.component';
import { VisorTextComponent } from './../visor/components/visor-text/visor-text.component';
import { TopBarComponent } from './../layouts/top-bar/top-bar.component';
import { LeftSidebarComponent } from './../layouts/left-sidebar/left-sidebar.component';
import { RightSidebarComponent } from './../layouts/right-sidebar/right-sidebar.component';
import { TemplateGalleryComponent } from '../constructor/template-gallery/template-gallery.component';
import { FileUploadComponent } from '../file-upload/file-upload.component';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { VgCoreModule } from 'videogular2/compiled/core';
import { VgControlsModule } from 'videogular2/compiled/controls';
import { VgOverlayPlayModule } from 'videogular2/compiled/overlay-play';
import { VgBufferingModule } from 'videogular2/compiled/buffering';
import { VgStreamingModule } from 'videogular2/compiled/streaming';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    ConstructorSharedModule,
    AngularEditorModule,
    VgCoreModule,
    VgControlsModule,
    VgOverlayPlayModule,
    VgBufferingModule,
    VgStreamingModule,
    FormsModule
  ],
  declarations: [
    ConstructorLayoutComponent,
    ConstructorFilmstripComponent,
    ConstructorVisorContainerComponent,
    ConstructorComponentContainerComponent,
    ConstructorTextComponent,
    ContentBlock3Component,
    VisorTextComponent,
    TopBarComponent,
    LeftSidebarComponent,
    RightSidebarComponent,
    TemplateGalleryComponent,
    FileUploadComponent
  ]
})
export class ConstructorLayoutModule {}
