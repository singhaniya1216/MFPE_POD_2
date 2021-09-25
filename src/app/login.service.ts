import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Demand } from './demand';
import { JwtReponse } from './jwt-reponse';
import { MedicineStock } from './medicine-stock';
import { RepresentativeSchedule } from './representative-schedule';
import { Supply } from './supply';
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
  stock()
  {
    return this.httpClient.get<MedicineStock[]>(`${this.baseURL}/MedicineStockInformation`);
  }

  order(token:String,demand:Demand[])
  {
    const headers = new HttpHeaders().set('Authorization', "Bearer "+token).set('Access-Control-Allow-Origin', '*');
    return this.httpClient.post<Supply[]>(`${this.baseURL}/PharmacySupply`, demand,{headers});
  }

  supplyT(token:String){
    const headers = new HttpHeaders().set('Authorization', "Bearer "+token).set('Access-Control-Allow-Origin', '*');
    return this.httpClient.get<Supply[]>(`${this.baseURL}/getMedicineSupply`,{headers});
  }

  demandT(token:String){
    const headers = new HttpHeaders().set('Authorization', "Bearer "+token).set('Access-Control-Allow-Origin', '*');
    return this.httpClient.get<Demand[]>(`${this.baseURL}/getMedicineDemand`,{headers});
  }
}
