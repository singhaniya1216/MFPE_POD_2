import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-nav-design',
  templateUrl: './nav-design.component.html',
  styleUrls: ['./nav-design.component.css']
})
export class NavDesignComponent implements OnInit {

  public isCollapsed = true;

  isLoginbutton = true;

  link: String = "/login";
  text: String = "Login";

  constructor(private loginservice: LoginService, private router: Router) { }

  ngOnInit(): void {
    this.check(LoginService.token);
  }

  getChecked(){
    this.check(LoginService.token);
  }

  check(token: String) {
    this.loginservice.check(token).subscribe(
      data => {
        if (data) {
          this.isLoginbutton = false;
          this.link = "/logout";
          this.text = "Logout";
        }
        else {
          this.isLoginbutton = true;
          this.link = "/login";
          this.text = "Login";
        }
      });
  }

}
