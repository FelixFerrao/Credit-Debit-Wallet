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
    private userService: UserService,
  ) {}

  ngOnInit(): void {}
  loggedInUser = new User();
  user = new User();

  login(userData: any) {
    // Login Part
    this.userService.userLogin(userData.email).subscribe(
      (data) => {
        this.loggedInUser = data;
        if (data.password != userData.password) {
          alert('Incorrect email or password')
        } else {
          this.userService.storeUserData(data);
          console.log(this.loggedInUser);
          alert('Login successful');
          this.routing.navigate(['/wallet']);
        }
      },
      (err) => {
        console.log('Error occured: ' + err);
      }
    );
  }
}
