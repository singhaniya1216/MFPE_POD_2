import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { Supply } from '../supply';

@Component({
  selector: 'app-supply-table',
  templateUrl: './supply-table.component.html',
  styleUrls: ['./supply-table.component.css']
})
export class SupplyTableComponent implements OnInit {

  public supply: Array<Supply> = new Array<Supply>();

  constructor(private loginservice: LoginService, private router: Router) { }

  ngOnInit(): void {
    this.check(LoginService.token);
    this.supplied();
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

  supplied(){
    this.loginservice.supplyT(LoginService.token).subscribe(
      data =>{
        this.supply = data;
        console.log(this.supply);
      },
      error => console.log(error));
  }

}
