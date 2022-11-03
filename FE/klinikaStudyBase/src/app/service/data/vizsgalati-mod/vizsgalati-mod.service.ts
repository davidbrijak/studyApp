import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {VizsgalatiModDetailDto} from "../../../dto/vizsgalatiModDetailDto";

@Injectable({
  providedIn: 'root'
})
export class VizsgalatiModService {

  baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<VizsgalatiModDetailDto[]>(this.baseUrl + `/api/vizsgalatiMod/list`)
  }

  create(newVm: VizsgalatiModDetailDto) {
    return this.http.post(
      this.baseUrl + `/api/vizsgalatiMod`,
      newVm)
  }
}
