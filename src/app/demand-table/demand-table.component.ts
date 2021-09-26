import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Demand } from '../demand';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-demand-table',
  templateUrl: './demand-table.component.html',
  styleUrls: ['./demand-table.component.css']
})
export class DemandTableComponent implements OnInit {

 
  public demand: Array<Demand> = new Array<Demand>();

  constructor(private loginservice: LoginService, private router: Router) { }

  ngOnInit(): void {
    this.check(LoginService.token);
    this.demanded();
  }

  gotoLoginPage() {
    this.router.navigate(['/expired']);
  }

  check(token:String){
    this.loginservice.check(token).subscribe(
      data =>{
        if(!data)
        {
          this.gotoLoginPage();
        }
      },
      error => console.log(error));
  }

  demanded(){
    this.loginservice.demandT(LoginService.token).subscribe(
      data =>{
        this.demand = data;
        console.log(this.demand);
      },
      error => console.log(error));
  }

}
