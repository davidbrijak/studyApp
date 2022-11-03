import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SzervezetTipus} from "../../szervezet-tipus-list/szervezet-tipus-list.component";

@Injectable({
  providedIn: 'root'
})
export class SzervezetTipusService {

  baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<SzervezetTipus[]>(this.baseUrl + `/api/szervezetTipus/list`)
  }

  create(ujSzervezetTipus: SzervezetTipus) {
    return this.http.post(
      this.baseUrl + `/api/szervezetTipus`,
      ujSzervezetTipus)
  }

  delete(id: number) {
    return this.http.delete(
      this.baseUrl + `/api/szervezetTipus/${id}`,
    )
  }

  get(id: number) {
    return this.http.get<SzervezetTipus>(
      this.baseUrl + `/api/szervezetTipus/${id}`,
    )
  }
}
