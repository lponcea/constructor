import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INumeroGrado } from 'app/shared/model/numero-grado.model';
import { NumeroGradoService } from './numero-grado.service';

@Component({
  templateUrl: './numero-grado-delete-dialog.component.html'
})
export class NumeroGradoDeleteDialogComponent {
  numeroGrado?: INumeroGrado;

  constructor(
    protected numeroGradoService: NumeroGradoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.numeroGradoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('numeroGradoListModification');
      this.activeModal.close();
    });
  }
}
