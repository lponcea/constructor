import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFicha } from 'app/shared/model/ficha.model';
import { FichaService } from './ficha.service';

@Component({
  templateUrl: './ficha-delete-dialog.component.html'
})
export class FichaDeleteDialogComponent {
  ficha?: IFicha;

  constructor(protected fichaService: FichaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fichaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fichaListModification');
      this.activeModal.close();
    });
  }
}
