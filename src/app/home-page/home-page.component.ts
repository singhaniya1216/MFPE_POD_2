import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(appcomp:AppComponent, router:Router) {
    if((LoginService.token == null) || (LoginService.token == "")){
      router.navigate(['/login']);
    }
   }

  ngOnInit(): void {
  }

}
