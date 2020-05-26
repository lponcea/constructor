import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TextEditorBehaviorService {
  private showTextEditor = new Subject<boolean>();

  constructor() {}

  getShowTextEditor(): Observable<boolean> {
    return this.showTextEditor.asObservable();
  }

  setShowTextEditor(showTextEditor: boolean): void {
    this.showTextEditor.next(showTextEditor);
  }
}
