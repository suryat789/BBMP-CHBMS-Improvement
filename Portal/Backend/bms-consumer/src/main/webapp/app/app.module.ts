import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { BmsSharedModule } from 'app/shared/shared.module';
import { BmsCoreModule } from 'app/core/core.module';
import { BmsAppRoutingModule } from './app-routing.module';
import { BmsHomeModule } from './home/home.module';
import { BmsEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    BmsSharedModule,
    BmsCoreModule,
    BmsHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    BmsEntityModule,
    BmsAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class BmsAppModule {}
