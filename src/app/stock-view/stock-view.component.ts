import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Demand } from '../demand';
import { LoginService } from '../login.service';
import { MedicineStock } from '../medicine-stock';

@Component({
  selector: 'app-stock-view',
  templateUrl: './stock-view.component.html',
  styleUrls: ['./stock-view.component.css']
})
export class StockViewComponent implements OnInit {

  public medicines: Array<MedicineStock> = new Array<MedicineStock>();

  constructor(private loginservice: LoginService, private router: Router) {
   // this.mstock();
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
