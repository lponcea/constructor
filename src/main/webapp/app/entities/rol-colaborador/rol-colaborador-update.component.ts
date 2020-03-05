import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRolColaborador, RolColaborador } from 'app/shared/model/rol-colaborador.model';
import { RolColaboradorService } from './rol-colaborador.service';

@Component({
  selector: 'jhi-rol-colaborador-update',
  templateUrl: './rol-colaborador-update.component.html'
})
export class RolColaboradorUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    descripcion: []
  });

  constructor(protected rolColaboradorService: RolColaboradorService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rolColaborador }) => {
      this.updateForm(rolColaborador);
    });
  }

  updateForm(rolColaborador: IRolColaborador): void {
    this.editForm.patchValue({
      id: rolColaborador.id,
      descripcion: rolColaborador.descripcion
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rolColaborador = this.createFromForm();
    if (rolColaborador.id !== undefined) {
      this.subscribeToSaveResponse(this.rolColaboradorService.update(rolColaborador));
    } else {
      this.subscribeToSaveResponse(this.rolColaboradorService.create(rolColaborador));
    }
  }

  private createFromForm(): IRolColaborador {
    return {
      ...new RolColaborador(),
      id: this.editForm.get(['id'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRolColaborador>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
