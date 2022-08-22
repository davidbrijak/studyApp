import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FizetesiPeriodus} from "../../fizetesi-periodus-list/fizetesi-periodus-list.component";
import {FizetesCheckListDto} from "../../kifizetesek/kifizetesek.component";

@Injectable({
  providedIn: 'root'
})
export class FizetesiPeriodusService {

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<FizetesiPeriodus[]>(`http://localhost:8080/api/fizetesiPeriodus/list`)
  }

  create(ujFizetesiPeriodus: FizetesiPeriodus) {
    return this.http.post(
      `http://localhost:8080/api/fizetesiPeriodus`,
      ujFizetesiPeriodus)
  }

  get(id: number | undefined) {
    return this.http.get<FizetesiPeriodus>(
      `http://localhost:8080/api/fizetesiPeriodus/${id}`,
    )
  }

  delete(id: number) {
    return this.http.delete(
      `http://localhost:8080/api/fizetesiPeriodus/${id}`,
    )
  }

}
