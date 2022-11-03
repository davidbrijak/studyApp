import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NewSzervezetDto, Szervezet} from "../../szervezet-list/szervezet-list.component";

@Injectable({
  providedIn: 'root'
})
export class SzervezetService {

  baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Szervezet[]>(this.baseUrl + `/api/szervezet/list`)
  }

  getAllByTipus(id: number) {
    return this.http.get<Szervezet[]>(this.baseUrl + `/api/szervezet/tipus/list/${id}`)
  }

  create(ujSzervezet: NewSzervezetDto) {
    return this.http.post(this.baseUrl + `/api/szervezet`, ujSzervezet)
  }

  delete(id: number) {
    return this.http.delete(this.baseUrl + `/api/szervezet/${id}`)
  }
}
