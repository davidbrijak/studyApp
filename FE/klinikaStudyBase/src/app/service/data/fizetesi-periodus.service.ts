import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FizetesiPeriodus} from "../../fizetesi-periodus-list/fizetesi-periodus-list.component";

@Injectable({
  providedIn: 'root'
})
export class FizetesiPeriodusService {

  constructor(private http: HttpClient) {
  }

  baseUrl = "http://localhost:8080";

  getAll() {
    return this.http.get<FizetesiPeriodus[]>(this.baseUrl + `/api/fizetesiPeriodus/list`)
  }

  create(ujFizetesiPeriodus: FizetesiPeriodus) {
    return this.http.post(this.baseUrl + `/api/fizetesiPeriodus`, ujFizetesiPeriodus)
  }

  get(id: number | undefined) {
    return this.http.get<FizetesiPeriodus>(this.baseUrl + `/api/fizetesiPeriodus/${id}`,
    )
  }

  delete(id: number) {
    return this.http.delete(this.baseUrl + `/api/fizetesiPeriodus/${id}`,
    )
  }

}
