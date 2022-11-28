import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {StudyDetailDto} from "../../study/study.component";
import {MeresDetailDto, NewStudyDto} from "../../study/uj-study/uj-study.component";
import {BaseService} from "../BaseService";

@Injectable({
  providedIn: 'root'
})
export class StudyService extends BaseService {

  getAll() {
    return this.http.get<StudyDetailDto[]>(this.baseUrl + `/api/study/list`)
  }


  get(id: number) {
    return this.http.get<NewStudyDto>(this.baseUrl + `/api/study//${id}`)
  }

  getMeresekByStudy(id: number) {
    return this.http.get<MeresDetailDto[]>(this.baseUrl + `/api/meres/list/${id}`)
  }

  export(id: number) {
    return this.http.get<any>(
      this.baseUrl + `/api/study/export/${id}`, {responseType: 'arraybuffer' as 'json'})
  }

  create(newStudy: NewStudyDto) {
    return this.http.post(
      this.baseUrl + `/api/study`,
      newStudy)
  }
}
