import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { JwtReponse } from '../jwt-reponse';
import { LoginService } from '../login.service';
import { UserToken } from '../user-token';
import { Usercredentials } from '../usercredentials';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  usercredentials: Usercredentials = new Usercredentials();
  usertoken: UserToken = new UserToken();
  jwt: JwtReponse = new JwtReponse();

  constructor(private loginservice: LoginService,
    private router: Router) {
      this.check(LoginService.token);
  }

  ngOnInit(): void {
    this.check(LoginService.token);
  }

  onSubmit(form: any) {
    this.usercredentials.userId = form.userid;
    this.usercredentials.password = form.password;
    console.log(this.usercredentials);
    this.login();
  }

  login() {
    this.loginservice.login(this.usercredentials).subscribe(
      data => {
        this.usertoken.authToken = data.authToken;
        this.usertoken.userId = data.userId;
        console.log(this.usertoken);
        LoginService.setToken(this.usertoken.authToken);
        console.log(LoginService.token);
        this.validate(LoginService.token);
      },
      error => console.log(error));
  }


  validate(token: String) {
    this.loginservice.validate(token).subscribe(
      data => {
        this.jwt = <JwtReponse>data;
        console.log(this.jwt);
        if (this.jwt.valid == true) {
          this.gotoHomePage();
        }
      },
      error => console.log(error));
  }
  
  gotoHomePage() {
    this.router.navigate(['/home']);
  }

  check(token: String) {
    this.loginservice.check(token).subscribe(
      data => {
        if (data === true) {
          this.gotoHomePage();
        }
      },
      error => console.log(error));
  }

}
