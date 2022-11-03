import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {UserCredentialsDto} from "../dto/UserCredentialsDto";

@Injectable({
  providedIn: 'root'
})
export class UserCredentialsService {

  baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient) {
  }

  login(userCredentials: UserCredentialsDto) {
    return this.http.post(
      this.baseUrl + `/api/authentication/login`,
      userCredentials)
  }
}
