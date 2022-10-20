import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}
  url = 'http://localhost:8080/account';
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

  userLogin(email: string) {
    return this.http.get<User>(this.url + '/' + email);
  }
}
