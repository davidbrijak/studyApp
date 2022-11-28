import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {JogosutsagDetailDto} from "../../jogosultsag/jogosultsag-list.component";
import {BaseService} from "../BaseService";

@Injectable({
  providedIn: 'root'
})
export class JogosultsagService extends BaseService {

  getAll() {
    return this.http.get<JogosutsagDetailDto[]>(this.baseUrl + `/api/jogosultsag/list`)
  }

  create(jogosultsagDetailDto: JogosutsagDetailDto) {
    return this.http.post(this.baseUrl +`/api/jogosultsag`, jogosultsagDetailDto)
  }

  get(id: number) {
    return this.http.get<JogosutsagDetailDto>(this.baseUrl + `/api/jogosultsag/${id}`,)
  }
}
