import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { IBloqueComponentes } from 'app/shared/model/bloque-componentes.model';

@Injectable({
  providedIn: 'root'
})
export class ContentBlocksService {
  selectedBlock = new Subject<any>();
  contentBlocks = new Subject<IBloqueComponentes[]>();
  indexBlockToDelete = new Subject<number>();

  constructor() {}

  getSelectedBlock(): Observable<any> {
    return this.selectedBlock.asObservable();
  }

  setSelectedBlock(selectedBlock: any): void {
    this.selectedBlock.next({
      selectedBlock
    });
  }

  getContentBlocks(): Observable<IBloqueComponentes[]> {
    return this.contentBlocks.asObservable();
  }

  setContentBlocks(contentBlocks: IBloqueComponentes[]): void {
    this.contentBlocks.next(contentBlocks);
  }

  getIndexBlockToDelete(): Observable<number> {
    return this.indexBlockToDelete.asObservable();
  }

  setIndexBlockToDelete(index: number): void {
    this.indexBlockToDelete.next(index);
  }
}
