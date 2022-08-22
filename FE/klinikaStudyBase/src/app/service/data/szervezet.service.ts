import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NewSzervezetDto, Szervezet} from "../../szervezet-list/szervezet-list.component";

@Injectable({
  providedIn: 'root'
})
export class SzervezetService {

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Szervezet[]>(`http://localhost:8080/api/szervezet/list`)
  }

  getAllByTipus(id: number) {
    return this.http.get<Szervezet[]>(`http://localhost:8080/api/szervezet/tipus/list/${id}`)
  }

  create(ujSzervezet: NewSzervezetDto) {
    return this.http.post(
      `http://localhost:8080/api/szervezet`,
      ujSzervezet)
  }

  delete(id: number) {
    return this.http.delete(
      `http://localhost:8080/api/szervezet/${id}`,
    )
  }

}
