import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TextService {
  private text = new Subject<string>();
  private editing = new Subject<boolean>();
  private templateType = new Subject<string>();

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

  getTemplateType(): Observable<string> {
    return this.templateType.asObservable();
  }

  setTemplateType(templateType: string): void {
    console.error(templateType);
    this.templateType.next(templateType);
  }
}
