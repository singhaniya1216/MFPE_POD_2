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
  repSchs:RepresentativeSchedule[];

  scheduleDate:String;
  constructor(private loginservice: LoginService, private router: Router) { }

  ngOnInit(): void {
  }
  
  onSubmit(){
    console.log(this.scheduleDate);
    this.getSchedule();
  }
  getSchedule() {
    this.loginservice.repSchedule(LoginService.token,this.scheduleDate).subscribe(
      data =>{
        this.repSchs= data;
        console.log(this.repSchs);
        this.submitted = true;
      },
      error => console.log(error));
  }

}
