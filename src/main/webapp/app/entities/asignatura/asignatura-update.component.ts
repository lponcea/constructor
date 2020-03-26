import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAsignatura, Asignatura } from 'app/shared/model/asignatura.model';
import { AsignaturaService } from './asignatura.service';

@Component({
  selector: 'jhi-asignatura-update',
  templateUrl: './asignatura-update.component.html'
})
export class AsignaturaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    descripcion: []
  });

  constructor(protected asignaturaService: AsignaturaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ asignatura }) => {
      this.updateForm(asignatura);
    });
  }

  updateForm(asignatura: IAsignatura): void {
    this.editForm.patchValue({
      id: asignatura.id,
      descripcion: asignatura.descripcion
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const asignatura = this.createFromForm();
    if (asignatura.id !== undefined) {
      this.subscribeToSaveResponse(this.asignaturaService.update(asignatura));
    } else {
      this.subscribeToSaveResponse(this.asignaturaService.create(asignatura));
    }
  }

  private createFromForm(): IAsignatura {
    return {
      ...new Asignatura(),
      id: this.editForm.get(['id'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAsignatura>>): void {
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
