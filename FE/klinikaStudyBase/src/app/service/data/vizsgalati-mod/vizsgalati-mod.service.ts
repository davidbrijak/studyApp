import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {VizsgalatiModDetailDto} from "../../../dto/vizsgalatiModDetailDto";
import {BaseService} from "../../BaseService";

@Injectable({
  providedIn: 'root'
})
export class VizsgalatiModService extends BaseService {

  getAll() {
    return this.http.get<VizsgalatiModDetailDto[]>(this.baseUrl + `/api/vizsgalatiMod/list`)
  }

  create(newVm: VizsgalatiModDetailDto) {
    return this.http.post(
      this.baseUrl + `/api/vizsgalatiMod`,
      newVm)
  }
}
