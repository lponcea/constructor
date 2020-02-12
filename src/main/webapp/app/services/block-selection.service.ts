import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BlockSelectionService {
  private block = new Subject<any>();

  constructor() {}

  sendMessage(message: string): void {
    this.block.next({ text: message });
  }
}
