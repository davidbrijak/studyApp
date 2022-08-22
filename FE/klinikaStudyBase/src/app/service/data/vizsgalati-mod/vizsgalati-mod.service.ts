import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {VizsgalatiModDetailDto} from "../../../dto/vizsgalatiModDetailDto";

@Injectable({
  providedIn: 'root'
})
export class VizsgalatiModService {

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<VizsgalatiModDetailDto[]>(`http://localhost:8080/api/vizsgalatiMod/list`)
  }

  create(newVm: VizsgalatiModDetailDto) {
    return this.http.post(
      `http://localhost:8080/api/vizsgalatiMod`,
      newVm)
  }
}
