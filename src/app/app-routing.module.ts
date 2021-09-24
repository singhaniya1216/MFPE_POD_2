import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { ScheduleComponent } from './schedule/schedule.component';


const routes: 
Routes = [{path:'login', component:LoginPageComponent},
{path: '', redirectTo: 'home', pathMatch: 'full'},
{path:'home', component:HomePageComponent},
{path:'RepSchedule', component:ScheduleComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
