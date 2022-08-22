import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {StudyDetailDto} from "../../study/study.component";
import {MeresDetailDto, NewStudyDto} from "../../study/uj-study/uj-study.component";

@Injectable({
  providedIn: 'root'
})
export class StudyService {

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<StudyDetailDto[]>(`http://localhost:8080/api/study/list`)
  }


  get(id: number) {
    return this.http.get<NewStudyDto>(`http://localhost:8080/api/study//${id}`)
  }

  getMeresekByStudy(id: number) {
    return this.http.get<MeresDetailDto[]>(`http://localhost:8080/api/meres/list/${id}`)
  }

  export(id: number) {
    return this.http.get<any>(
      `http://localhost:8080/api/study/export/${id}`, {responseType: 'arraybuffer' as 'json'})
  }

  create(newStudy: NewStudyDto) {
    return this.http.post(
      `http://localhost:8080/api/study`,
      newStudy)
  }
}
