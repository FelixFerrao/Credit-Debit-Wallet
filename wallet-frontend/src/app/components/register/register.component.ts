import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { registerDTO } from 'src/app/dto/userRegisterDTO';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerObject = new registerDTO()
  constructor(private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
    if(this.userService.getUserData().id) {
      this.router.navigate(['/wallet']);
    }
  }

  register(data: any) {
    this.registerObject.name = data.username;
    this.registerObject.email = data.email;
    this.registerObject.password = data.password;
    console.log(this.registerObject);
    this.userService.userRegister(this.registerObject).subscribe(
      (data) => {
        alert('Registered successfully');
        this.router.navigate(['/login']);
      },
      (err) => {
        alert('An error occured: ' + err);
      }
    );
  }

}
