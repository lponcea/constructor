import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGradoAcademico } from 'app/shared/model/grado-academico.model';
import { GradoAcademicoService } from './grado-academico.service';

@Component({
  templateUrl: './grado-academico-delete-dialog.component.html'
})
export class GradoAcademicoDeleteDialogComponent {
  gradoAcademico?: IGradoAcademico;

  constructor(
    protected gradoAcademicoService: GradoAcademicoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.gradoAcademicoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('gradoAcademicoListModification');
      this.activeModal.close();
    });
  }
}
