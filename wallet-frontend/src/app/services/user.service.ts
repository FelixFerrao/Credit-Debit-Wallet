import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { registerDTO } from '../dto/userRegisterDTO';
import { User } from './user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}
  url = 'http://localhost:8080/wallet';
  user = new User();

  storeUserData(loggedInUser: User) {
    this.user.email = loggedInUser.email;
    this.user.id = loggedInUser.id;
    this.user.balance = loggedInUser.balance;
    this.user.description = loggedInUser.description;
  }

  getUserData() {
    return this.user;
  }

  userRegister(user: registerDTO) {
    return this.http.post<User>(this.url, user);
  }

  userLogin(email: string): Observable<User> {
    return this.http.get<User>(this.url + '/' + email);
  }

  getUserDataByEmail(user_email: string): Observable<User> {
    return this.http.get<User>(this.url + '/' + user_email);
  }
}
