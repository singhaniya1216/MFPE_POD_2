import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { OrderMedicineComponent } from './order-medicine/order-medicine.component';
import { SupplyTableComponent } from './supply-table/supply-table.component';
import { DemandTableComponent } from './demand-table/demand-table.component';
import { StockViewComponent } from './stock-view/stock-view.component';
import { ExpiredSessionComponent } from './expired-session/expired-session.component';


const routes: 
Routes = [{path:'login', component:LoginPageComponent},
{path: '', redirectTo: 'home', pathMatch: 'full'},
{path:'home', component:HomePageComponent},
{path:'RepSchedule', component:ScheduleComponent},
{path:'OrderMedicine', component:OrderMedicineComponent},
{path:'Supply', component:SupplyTableComponent},
{path:'Demand', component:DemandTableComponent},
{path:'Stock', component:StockViewComponent},
{path:'expired', component:ExpiredSessionComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
