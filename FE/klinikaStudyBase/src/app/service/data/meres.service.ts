import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NewMeres} from "../../meres/uj-meres/uj-meres.component";
import {MeresDetailDto} from "../../study/uj-study/uj-study.component";

@Injectable({
  providedIn: 'root'
})
export class MeresService {

  constructor(private http: HttpClient) {
  }

  getMeresekByStudy(id: number) {
    return this.http.get<MeresDetailDto[]>(`http://localhost:8080/api/meres/list/${id}`)
  }

  get(id: number) {
    return this.http.get<NewMeres>(`http://localhost:8080/api/meres/${id}`)
  }

  create(newMeres: NewMeres) {
    return this.http.post(
      `http://localhost:8080/api/meres`,
      newMeres)
  }}
