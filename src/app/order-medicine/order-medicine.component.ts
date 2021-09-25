import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { MedicineStock } from '../medicine-stock';

@Component({
  selector: 'app-order-medicine',
  templateUrl: './order-medicine.component.html',
  styleUrls: ['./order-medicine.component.css']
})
export class OrderMedicineComponent implements OnInit {

  medicines:MedicineStock[];
  constructor(private loginservice: LoginService, private router: Router) {
    this.mstock();
   }

  ngOnInit(): void {
    this.mstock();
  }


  mstock() {
  this.loginservice.stock().subscribe(
    data => {
      this.medicines = data;
      console.log(this.medicines);
    },
    error => console.log(error));
  }

}
