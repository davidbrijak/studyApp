import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FizetesCheckListDto} from "../../kifizetesek/kifizetesek.component";

@Injectable({
  providedIn: 'root'
})
export class KifizetesekService {

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<FizetesCheckListDto[]>(`http://localhost:8080/api/kifizetesekCheck/list`)
  }

  getById(id: number) {
    return this.http.get<FizetesCheckListDto[]>(`http://localhost:8080/api/kifizetesekCheck/list/${id}`)
  }

  change(fizetesCheckListDto:  FizetesCheckListDto[]) {
    return this.http.post(
      `http://localhost:8080/api/kifizetesekCheck/change`, fizetesCheckListDto)
  }
}
