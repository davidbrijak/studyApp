import { Injectable } from '@angular/core';
import {Szerepkor} from "../../szerepkor-list/szerepkor-list.component";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SzerepkorServiceService {

  baseUrl = "http://localhost:8080";
  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Szerepkor[]>(this.baseUrl + `/api/szerepkor/list`);
  }

  hasAnySzemelyExistsBySzerepkor(id: number) {
    return this.http.get<Boolean>(this.baseUrl + `/api/szemely/hasAnySzemelyBySzerepkor/${id}`);
  }

  create(ujszerep: Szerepkor) {
    return this.http.post(this.baseUrl + `/api/szerepkor`, ujszerep)
  }

  delete(id: number) {
    return this.http.delete(this.baseUrl + `/api/szerepkor/${id}`)
  }

  get(id: number) {
    return this.http.get<Szerepkor>(
      this.baseUrl + `/api/szerepkor/${id}`,
    )
  }
}
