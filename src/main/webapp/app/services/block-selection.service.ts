import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

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
