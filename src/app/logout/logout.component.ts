import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private loginservice: LoginService, private router: Router) {
    LoginService.setToken("");
   }

  ngOnInit(): void {
    LoginService.setToken("");
  }

}
