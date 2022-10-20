import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(private http: HttpClient, private routing: RouterLink) {}

  ngOnInit(): void {}
  user = new User();
  login(data: any) {
    this.user.email = data.email;
    this.user.password = data.email;
  }
}
