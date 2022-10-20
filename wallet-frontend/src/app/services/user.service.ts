import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  url = 'http://localhost:8080/account';
  constructor(private http: HttpClient) {}
  email: string | undefined;
  user_id: number | undefined;
  balance: number | undefined;

  storeUserData(user: User) {
    this.email = user.email;
    this.user_id = user.id;
    this.balance = user.balance;
  }

  userLogin(email: string, password: string) {
    this.http.post(this.url, '/' + email + '/' + password).subscribe(data);
  }
}
