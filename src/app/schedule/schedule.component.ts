import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { RepresentativeSchedule } from '../representative-schedule';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

  submitted = false;
  repSchs: RepresentativeSchedule[];

  today:Date = new Date();
  time:Time;
  minDate:string = this.today.getFullYear().toString()+'-0'+(this.today.getMonth()+1)+'-'+this.today.getDate().toString();


scheduleDate: String;
constructor(private loginservice: LoginService, private router: Router) {
  console.log(this.minDate);
}

ngOnInit(): void {
  this.check(LoginService.token);
}

gotoLoginPage() {
  this.router.navigate(['/expired']);
}

check(token:String){
  this.loginservice.check(token).subscribe(
    data =>{
      if(data === false)
      {
        this.gotoLoginPage();
      }
    },
    error => console.log(error));
}

onSubmit(form: any){
  this.scheduleDate = form.scheduleDate;
  console.log(this.scheduleDate);
  this.getSchedule();
}
getSchedule() {
  this.loginservice.repSchedule(LoginService.token, this.scheduleDate).subscribe(
    data => {
      this.repSchs = data;
      console.log(this.repSchs);
      this.submitted = true;
    },
    error => console.log(error));
}

}
