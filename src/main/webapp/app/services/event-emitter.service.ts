import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {
  invokeSave = new Subject<any>();

  constructor() {}

  getInvokeSave(): Observable<any> {
    return this.invokeSave.asObservable();
  }

  setInvokeSave(): void {
    this.invokeSave.next();
  }
}
