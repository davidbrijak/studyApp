import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {JogosutsagDetailDto} from "../../jogosultsag/jogosultsag-list.component";

@Injectable({
  providedIn: 'root'
})
export class JogosultsagService {

  baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient) {
  }

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
