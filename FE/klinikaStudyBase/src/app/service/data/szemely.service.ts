import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Szemely} from "../../study/uj-study/uj-study.component";
import {JogosutsagDetailDto} from "../../jogosultsag/jogosultsag-list.component";

@Injectable({
  providedIn: 'root'
})
export class SzemelyService {

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Szemely[]>(`http://localhost:8080/api/szemely/list`)
  }

  create(szemely: Szemely) {
    return this.http.post(
      `http://localhost:8080/api/szemely`,
      szemely)
  }
}
