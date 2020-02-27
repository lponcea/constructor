import { Injectable } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  public resorceUrl = SERVER_API_URL + '/api/uploadFile';

  constructor(private http: HttpClient) {}

  pushFileStorage(file: File): Observable<any> {
    const data: FormData = new FormData();
    data.append('file', file);
    return this.http.post(this.resorceUrl, data);
  }

  getFile(): Observable<any> {
    const data: FormData = new FormData();
    data.append('file', 'test');
    return this.http.get(SERVER_API_URL + '/api/loadVideo');
  }
}
