import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  token: string | null
  constructor(private router: Router) { }

  ngOnInit(): void {
    this.token = localStorage.getItem('refreshToken');
    if (this.token == null) {
      this.router.navigate(['auth/login']);
    } else {
      this.router.navigate(['fizetesiPeriodus/list']);
    }
  }

  deleteToken() {
    localStorage.clear()
    this.ngOnInit()
  }

}
