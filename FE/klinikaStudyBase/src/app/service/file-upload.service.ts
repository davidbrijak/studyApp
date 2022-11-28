import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import {BaseService} from "./BaseService";

@Injectable({
  providedIn: 'root'
})
export class FileUploadService extends BaseService {
  filebaseUrl = this.baseUrl + "/document";

  upload(studyId: number, file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', `${this.filebaseUrl}/${studyId}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }

  getFiles(studyId: number): Observable<any> {
    return this.http.get(`${this.filebaseUrl}/${studyId}/files`);
  }

  getFile(id: string): Observable<any> {
    const httpOptions = {
      responseType: 'blob' as 'json'
    };
    return this.http.get(this.filebaseUrl + `/document/files/${id}`, httpOptions);
  }

  delete(id: string): Observable<any> {
    return this.http.delete(this.filebaseUrl + `/document/files/delete/${id}`,);
  }
}
