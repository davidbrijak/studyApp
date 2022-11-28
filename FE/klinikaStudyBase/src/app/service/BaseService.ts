import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BaseService {

  baseUrl = "http://localhost:8090";

  constructor(protected http: HttpClient) {
  }
}
