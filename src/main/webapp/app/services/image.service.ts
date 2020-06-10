import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private imgSrc = new Subject<SafeUrl>();
  private editing = new Subject<boolean>();

  constructor(private sanitizer: DomSanitizer) {}

  setImgSrc(imgSrc: SafeUrl): void {
    this.imgSrc.next(imgSrc);
  }

  getImgSrc(): Observable<SafeUrl> {
    return this.imgSrc.asObservable();
  }

  setEditing(editing: boolean): void {
    this.editing.next(editing);
  }

  getEditing(): Observable<boolean> {
    return this.editing.asObservable();
  }
}
