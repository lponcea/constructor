import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRolColaborador } from 'app/shared/model/rol-colaborador.model';
import { RolColaboradorService } from './rol-colaborador.service';

@Component({
  templateUrl: './rol-colaborador-delete-dialog.component.html'
})
export class RolColaboradorDeleteDialogComponent {
  rolColaborador?: IRolColaborador;

  constructor(
    protected rolColaboradorService: RolColaboradorService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rolColaboradorService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rolColaboradorListModification');
      this.activeModal.close();
    });
  }
}
