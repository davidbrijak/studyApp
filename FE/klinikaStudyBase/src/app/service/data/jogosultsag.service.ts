import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {JogosutsagDetailDto} from "../../jogosultsag/jogosultsag-list.component";

@Injectable({
  providedIn: 'root'
})
export class JogosultsagService {

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<JogosutsagDetailDto[]>(`http://localhost:8080/api/jogosultsag/list`)
  }

  create(jogosultsagDetailDto: JogosutsagDetailDto) {
    return this.http.post(
      `http://localhost:8080/api/jogosultsag`,
      jogosultsagDetailDto)
  }

  get(id: number) {
    return this.http.get<JogosutsagDetailDto>(
      `http://localhost:8080/api/jogosultsag/${id}`,
    )
  }
}
