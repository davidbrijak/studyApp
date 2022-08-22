import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Szervezet} from "../../szervezet-list/szervezet-list.component";
import {SzervezetTipus} from "../../szervezet-tipus-list/szervezet-tipus-list.component";
import {FizetesiPeriodus} from "../../fizetesi-periodus-list/fizetesi-periodus-list.component";

@Injectable({
  providedIn: 'root'
})
export class SzervezetTipusService {

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<SzervezetTipus[]>(`http://localhost:8080/api/szervezetTipus/list`)
  }

  create(ujSzervezetTipus: SzervezetTipus) {
    return this.http.post(
      `http://localhost:8080/api/szervezetTipus`,
      ujSzervezetTipus)
  }

  delete(id: number) {
    return this.http.delete(
      `http://localhost:8080/api/szervezetTipus/${id}`,
    )
  }

  get(id: number) {
    return this.http.get<SzervezetTipus>(
      `http://localhost:8080/api/szervezetTipus/${id}`,
    )
  }

}
