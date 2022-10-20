import { Component, OnInit } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';
import { User } from 'src/app/services/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(
    private http: HttpClientModule,
    private routing: Router,
    private userService: UserService
  ) {}

  ngOnInit(): void {}
  loggedInUser = new User();
  user = new User();

  login(data: any) {
    // Login Part
    this.userService.userLogin(data.email).subscribe(
      (data) => {
        // console.log(data);
        this.loggedInUser = data;
        this.userService.storeUserData(data);
        console.log(this.loggedInUser);
      },
      (err) => {
        console.log('Error occured: ' + err);
      }
    );
  }
}
