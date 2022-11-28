import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NewMeres} from "../../meres/uj-meres/uj-meres.component";
import {MeresDetailDto} from "../../study/uj-study/uj-study.component";
import {BaseService} from "../BaseService";

@Injectable({
  providedIn: 'root'
})
export class MeresService extends BaseService {

  getMeresekByStudy(id: number) {
    return this.http.get<MeresDetailDto[]>(this.baseUrl + `/api/meres/list/${id}`)
  }

  get(id: number) {
    return this.http.get<NewMeres>(this.baseUrl + `/api/meres/${id}`)
  }

  create(newMeres: NewMeres) {
    return this.http.post(this.baseUrl + `/api/meres`, newMeres)
  }}
