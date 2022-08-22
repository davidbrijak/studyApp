import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  errorMessage = 'Invalid credentials!';
  invalidLogin = false;
  router: Router;

  constructor(router: Router) {
    this.router = router
  }

  ngOnInit(): void {
  }

  handleLogin() {
    if (this.username !== 'davidbrijak' || this.password !== 'password') {
      this.invalidLogin = true;
    } else {
      this.router.navigate(['welcome', this.username])
    }
  }
}
