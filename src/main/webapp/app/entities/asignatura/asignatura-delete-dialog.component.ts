import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAsignatura } from 'app/shared/model/asignatura.model';
import { AsignaturaService } from './asignatura.service';

@Component({
  templateUrl: './asignatura-delete-dialog.component.html'
})
export class AsignaturaDeleteDialogComponent {
  asignatura?: IAsignatura;

  constructor(
    protected asignaturaService: AsignaturaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.asignaturaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('asignaturaListModification');
      this.activeModal.close();
    });
  }
}
