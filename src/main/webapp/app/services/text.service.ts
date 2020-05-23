import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TextService {
  private text = new Subject<string>();
  private editing = new Subject<boolean>();
  private templateTypeId = new Subject<number>();

  constructor() {}

  getText(): Observable<string> {
    return this.text.asObservable();
  }

  setText(text: string): void {
    this.text.next(text);
  }

  getEditing(): Observable<boolean> {
    return this.editing.asObservable();
  }

  setEditing(editing: boolean): void {
    this.editing.next(editing);
  }

  getTemplateTypeId(): Observable<number> {
    return this.templateTypeId.asObservable();
  }

  setTemplateTypeId(templateType: number): void {
    this.templateTypeId.next(templateType);
  }
}
