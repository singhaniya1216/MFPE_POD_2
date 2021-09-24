import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtReponse } from './jwt-reponse';
import { RepresentativeSchedule } from './representative-schedule';
import { UserToken } from './user-token';
import { Usercredentials } from './usercredentials';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseURL = 'http://localhost:8083/api/portal';
  constructor(private httpClient: HttpClient) { }

  static token: String = "";

  static setToken(tk:String){
    this.token = tk;
  }


  login(usercredentials: Usercredentials){
    return this.httpClient.post<UserToken>(`${this.baseURL}/login`,usercredentials);
  }

  validate(token: String) : Observable<JwtReponse>{
    const headers = new HttpHeaders().set('Authorization', "Bearer "+token);
    return this.httpClient.get<JwtReponse>(`${this.baseURL}/validate`, {headers});
  }

  repSchedule(token:String,date:String): Observable<RepresentativeSchedule[]>
  {
    const headers = new HttpHeaders().set('Authorization', "Bearer "+token).set('Access-Control-Allow-Origin', '*');
    return this.httpClient.get<RepresentativeSchedule[]>(`${this.baseURL}/RepSchedule/${date}`, {headers});
  }
}
