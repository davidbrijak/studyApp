import { Injectable } from '@angular/core';
import {Szerepkor} from "../../szerepkor-list/szerepkor-list.component";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SzerepkorServiceService {

  constructor(
    private http: HttpClient
  ) { }

  getAll() {
    return this.http.get<Szerepkor[]>(`http://localhost:8080/api/szerepkor/list`);
  }

  hasAnySzemelyExistsBySzerepkor(id: number) {
    return this.http.get<Boolean>(`http://localhost:8080/api/szemely/hasAnySzemelyBySzerepkor/${id}`);
  }

  create(ujszerep: Szerepkor) {
    return this.http.post(
      `http://localhost:8080/api/szerepkor`,
      ujszerep)
  }

  delete(id: number) {
    return this.http.delete(
      `http://localhost:8080/api/szerepkor/${id}`,
      )
  }

  get(id: number) {
    return this.http.get<Szerepkor>(
      `http://localhost:8080/api/szerepkor/${id}`,
    )
  }
}
