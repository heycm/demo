import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataResult } from 'src/public/http/BaseEntity';
import { LoginData, LoginModel } from './entity';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  /** 登录 */
  login(model: LoginModel) {
    return this.http.post<DataResult<LoginData>>('/login', model).toPromise();
  }

  /** 单点登录 */
  sso() {
    return this.http.get<DataResult<LoginData>>('/sso').toPromise();
  }

}