import { Injectable, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventEmitterService {
  invokeSave = new EventEmitter();
  subsVar?: Subscription;

  constructor() {}

  onFileSaveClick(): void {
    this.invokeSave.emit();
  }
}
