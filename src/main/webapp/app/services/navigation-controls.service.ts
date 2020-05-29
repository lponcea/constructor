import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NavigationControlsService {
  private openTemplateGallery = new Subject<any>();
  private selectedTemplate = new Subject<any>();
  private visorSize = new Subject<string>();

  constructor() {}

  setOpenTemplateGallery(open: boolean): void {
    this.openTemplateGallery.next({
      openTemplateGallery: open
    });
  }

  getTemplateGalleryStatus(): Observable<any> {
    return this.openTemplateGallery.asObservable();
  }

  selectTemplate(selectedTemplate: any): void {
    this.selectedTemplate.next({
      selectedTemplate
    });
  }

  getSelectedTemplateType(): Observable<any> {
    return this.selectedTemplate.asObservable();
  }

  setVisorSize(size: string): void {
    this.visorSize.next(size);
  }

  getVisorSize(): Observable<any> {
    return this.visorSize.asObservable();
  }
}
