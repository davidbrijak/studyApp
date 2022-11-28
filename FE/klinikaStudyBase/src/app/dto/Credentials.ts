import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Credentials {
  private accessToken = '';

  getAccessToken(): string {
    return this.accessToken;
  }

  setAccessToken(token: string): void {
    this.accessToken = token;
  }
}
