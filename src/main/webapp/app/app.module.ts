import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { ConstructorSharedModule } from 'app/shared/shared.module';
import { ConstructorCoreModule } from 'app/core/core.module';
import { ConstructorAppRoutingModule } from './app-routing.module';
import { HomeModule } from './home/home.module';
import { ConstructorLayoutModule } from './constructor-layout/constructor-layout.module';
import { ConstructorEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { HttpClientModule } from '@angular/common/http';
import { CourseConfigurationComponent } from './course-configuration/course-configuration.component';
import { ContentBlock2Component } from './constructor/content-blocks/content-block2/content-block2.component';
import { HomePageComponent } from './home-page/home-page.component';
import { CursoUpdateComponent } from './entities/curso/curso-update.component';

@NgModule({
  imports: [
    BrowserModule,
    ConstructorSharedModule,
    ConstructorCoreModule,
    HomeModule,
    ConstructorLayoutModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    ConstructorEntityModule,
    ConstructorAppRoutingModule,
    HttpClientModule
  ],
  declarations: [
    MainComponent,
    NavbarComponent,
    ErrorComponent,
    PageRibbonComponent,
    ActiveMenuDirective,
    FooterComponent,
    CourseConfigurationComponent,
    ContentBlock2Component,
    HomePageComponent,
    CursoUpdateComponent
  ],
  bootstrap: [MainComponent]
})
export class ConstructorAppModule {}
