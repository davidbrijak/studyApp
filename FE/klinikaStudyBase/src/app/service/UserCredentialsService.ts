import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {UserCredentialsDto} from "../dto/UserCredentialsDto";
import {BaseService} from "./BaseService";

@Injectable({
  providedIn: 'root'
})
export class UserCredentialsService extends BaseService {

  login(userCredentials: UserCredentialsDto) {
    return this.http.post(
      this.baseUrl + `/api/authentication/login`,
      userCredentials)
  }
}
