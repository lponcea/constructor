import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NavigationControlsService {
  private openTemplateGallery = new Subject<any>();
  private openProperties = new Subject<boolean>();
  private selectedTemplate = new Subject<any>();
  private visorSize = new Subject<string>();

  constructor() {}

  setOpenTemplateGallery(open: boolean): void {
    if (open) {
      this.setOpenProperties(false);
    }
    this.openTemplateGallery.next({
      openTemplateGallery: open
    });
  }

  getTemplateGalleryStatus(): Observable<any> {
    return this.openTemplateGallery.asObservable();
  }

  setOpenProperties(openProperties: boolean): void {
    if (openProperties) {
      this.setOpenTemplateGallery(false);
    }
    this.openProperties.next(openProperties);
  }

  getOpenProperties(): Observable<boolean> {
    return this.openProperties.asObservable();
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
