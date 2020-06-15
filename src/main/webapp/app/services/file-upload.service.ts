import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { Observable } from 'rxjs';
import { ImageService } from './image.service';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  constructor(private http: HttpClient, private domSanitizer: DomSanitizer, private imageService: ImageService) {}

  pushFileStorage(file: File, id: number): Observable<any> {
    const data: FormData = new FormData();
    data.append('file', file);
    data.append('id', id.toString());
    return this.http.post(SERVER_API_URL + '/api/fileUpload', data);
  }

  getImageFile(filePath: string): Observable<HttpResponse<Blob>> {
    return this.http.get(SERVER_API_URL + '/api/loadImage?file=' + filePath, {
      observe: 'response',
      responseType: 'blob'
    });
  }

  getVideoFile(filePath: string): Observable<HttpResponse<Blob>> {
    return this.http.get(SERVER_API_URL + '/api/loadVideo?file=' + filePath, {
      observe: 'response',
      responseType: 'blob'
    });
  }

  getVideoPreviewFile(filePath: string): Observable<HttpResponse<Blob>> {
    return this.http.get(SERVER_API_URL + '/api/previewVideo?file=' + filePath, {
      observe: 'response',
      responseType: 'blob'
    });
  }

  deleteFile(filePath: string): Observable<any> {
    const data: FormData = new FormData();
    data.append('file', filePath);
    return this.http.delete(SERVER_API_URL + '/api/deleteFile?file=' + filePath, { responseType: 'text' });
  }

  public getImage(path: string): void {
    this.getImageFile(path).subscribe(data => {
      const imagePath = URL.createObjectURL(data.body);
      const objectUrl = this.domSanitizer.bypassSecurityTrustUrl(imagePath);
      this.imageService.setImgSrc(objectUrl);
    });
  }
}
