import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {FileUploadService} from "../service/file-upload.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-file-upload-component',
  templateUrl: './file-upload-component.component.html',
  styleUrls: ['./file-upload-component.component.css']
})
export class FileUploadComponentComponent implements OnInit {

  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';
  fileInfos?: Observable<any>;
  studyId: number;
  private blob: Blob;
  constructor(private uploadService: FileUploadService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.studyId = this.route.snapshot.params['id'];
    this.fileInfos = this.uploadService.getFiles(this.studyId);
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  download(id:string, name: string): void {
    this.uploadService.getFile(id).subscribe((data) => {
      this.blob = new Blob([data], {type: 'application/pdf'});
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = name;
      link.click();
    });
  }

  delete(id:string, name: string): void {
    this.uploadService.delete(id).subscribe(() => {
      this.ngOnInit()
    });
  }

  upload(): void {
    this.progress = 0;
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      if (file) {
        this.currentFile = file;
        this.uploadService.upload(this.studyId, this.currentFile).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round(100 * event.loaded / event.total);
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.uploadService.getFiles(this.studyId);
            }
          },
          (err: any) => {
            console.log(err);
            this.progress = 0;
            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }
            this.currentFile = undefined;
          });
      }
      this.selectedFiles = undefined;
    }
  }
}
