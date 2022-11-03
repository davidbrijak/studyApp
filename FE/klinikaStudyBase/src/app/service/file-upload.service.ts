import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  baseUrl = 'http://localhost:8080/document';
  constructor(private http: HttpClient) { }
  upload(studyId: number, file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', `${this.baseUrl}/${studyId}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }

  getFiles(studyId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${studyId}/files`);
  }

  getFile(id: string): Observable<any> {
    const httpOptions = {
      responseType: 'blob' as 'json'
    };
    return this.http.get(this.baseUrl + `/document/files/${id}`, httpOptions);
  }

  delete(id: string): Observable<any> {
    return this.http.delete(this.baseUrl + `/document/files/delete/${id}`,);
  }
}
