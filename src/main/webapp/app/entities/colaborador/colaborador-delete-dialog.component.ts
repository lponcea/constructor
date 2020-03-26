import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IColaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from './colaborador.service';

@Component({
  templateUrl: './colaborador-delete-dialog.component.html'
})
export class ColaboradorDeleteDialogComponent {
  colaborador?: IColaborador;

  constructor(
    protected colaboradorService: ColaboradorService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.colaboradorService.delete(id).subscribe(() => {
      this.eventManager.broadcast('colaboradorListModification');
      this.activeModal.close();
    });
  }
}
