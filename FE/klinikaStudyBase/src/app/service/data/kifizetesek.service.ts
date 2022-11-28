import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FizetesCheckListDto} from "../../kifizetesek/kifizetesek.component";
import {BaseService} from "../BaseService";

@Injectable({
  providedIn: 'root'
})
export class KifizetesekService extends BaseService {

  getAll() {
    return this.http.get<FizetesCheckListDto[]>(this.baseUrl + `/api/kifizetesekCheck/list`)
  }

  getById(id: number) {
    return this.http.get<FizetesCheckListDto[]>(this.baseUrl + `/api/kifizetesekCheck/list/${id}`)
  }

  change(fizetesCheckListDto:  FizetesCheckListDto[]) {
    return this.http.post(this.baseUrl + `/api/kifizetesekCheck/change`, fizetesCheckListDto)
  }
}
