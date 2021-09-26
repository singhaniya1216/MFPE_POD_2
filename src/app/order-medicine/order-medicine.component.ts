import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Demand } from '../demand';
import { LoginService } from '../login.service';
import { MedicineStock } from '../medicine-stock';
import { Supply } from '../supply';

@Component({
  selector: 'app-order-medicine',
  templateUrl: './order-medicine.component.html',
  styleUrls: ['./order-medicine.component.css']
})
export class OrderMedicineComponent implements OnInit {

  public demand: Array<Demand> = new Array<Demand>();
  public supply: Array<Supply> = new Array<Supply>();
  public medicines: Array<MedicineStock> = new Array<MedicineStock>();

  submitted = false;
  error = false;

  constructor(private loginservice: LoginService, private router: Router) {
    // this.mstock();
    this.check(LoginService.token);
    this.mstock();
  }

  ngOnInit(): void {
    this.check(LoginService.token);
    this.mstock();
  }

  gotoLoginPage() {
    this.router.navigate(['/expired']);
  }

  check(token: String) {
    this.loginservice.check(token).subscribe(
      data => {
        if (data === false) {
          this.gotoLoginPage();
        }
      },
      error => console.log(error));
  }


  mstock() {
    this.loginservice.stock().subscribe(
      data => {
        this.medicines = data;
        for (let i = 0; i < this.medicines.length; i++) {
          this.medicines[i].amount = 0;
        }
        console.log(this.medicines);
      },
      error => console.log(error));
  }

  onSubmit() {
    let x = 0;
    for (let i = 0; i < this.medicines.length; i++) {
      if (this.medicines[i].amount > 0) {
        if (this.medicines[i].amount <= this.medicines[i].numberOfTabletsInStock) {
          console.log(this.medicines[i].name);
          const d = new Demand(this.medicines[i].name, this.medicines[i].amount);
          this.demand[x] = d;
          x = x + 1;
        }
      }
    }
    if (this.error === false) {
      console.log(this.demand);
      this.loginservice.order(LoginService.token, this.demand).subscribe(
        data => {
          if(data.length > 0){
          this.supply = data;
          console.log(this.supply);
          this.submitted = true;
          this.mstock();
          }
          else{
            this.error = true;
          }
        },
        error => console.log(error));
    }
  }

}

