import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { OrderMedicineComponent } from './order-medicine/order-medicine.component';


const routes: 
Routes = [{path:'login', component:LoginPageComponent},
{path: '', redirectTo: 'home', pathMatch: 'full'},
{path:'home', component:HomePageComponent},
{path:'RepSchedule', component:ScheduleComponent},
{path:'OrderMedicine', component:OrderMedicineComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
