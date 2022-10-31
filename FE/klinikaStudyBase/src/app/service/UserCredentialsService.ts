import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {UserCredentialsDto} from "../dto/UserCredentialsDto";

@Injectable({
  providedIn: 'root'
})
export class UserCredentialsService {

  constructor(private http: HttpClient) {
  }

  login(userCredentials: UserCredentialsDto) {
    return this.http.post(
      `http://localhost:8080/api/authentication/login`,
      userCredentials)
  }
}
