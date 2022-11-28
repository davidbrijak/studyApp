import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Szemely} from "../../study/uj-study/uj-study.component";
import {SzemelyFilter} from "../../szemelyek-list/szemelyek-list.component";
import {BaseService} from "../BaseService";

@Injectable({
  providedIn: 'root'
})
export class SzemelyService extends BaseService {

  getAll() {
    return this.http.get<Szemely[]>(this.baseUrl + `/api/szemely/list`)
  }

  create(szemely: Szemely) {
    return this.http.post(this.baseUrl + `/api/szemely`, szemely)
  }

  search(filter: SzemelyFilter) {
    return this.http.post(this.baseUrl + `/api/szemely/search/filter`, filter)
  }
}
