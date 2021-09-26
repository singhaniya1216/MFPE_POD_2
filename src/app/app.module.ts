import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginPageComponent } from './login-page/login-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { ScheduleComponent } from './schedule/schedule.component';
import { NavDesignComponent } from './nav-design/nav-design.component';
import { OrderMedicineComponent } from './order-medicine/order-medicine.component';
import { SupplyTableComponent } from './supply-table/supply-table.component';
import { DemandTableComponent } from './demand-table/demand-table.component';
import { StockViewComponent } from './stock-view/stock-view.component';
import { ExpiredSessionComponent } from './expired-session/expired-session.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HomePageComponent,
    ScheduleComponent,
    NavDesignComponent,
    OrderMedicineComponent,
    SupplyTableComponent,
    DemandTableComponent,
    StockViewComponent,
    ExpiredSessionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
