import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGradoAcademico, GradoAcademico } from 'app/shared/model/grado-academico.model';
import { GradoAcademicoService } from './grado-academico.service';

@Component({
  selector: 'jhi-grado-academico-update',
  templateUrl: './grado-academico-update.component.html'
})
export class GradoAcademicoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    descripcion: []
  });

  constructor(protected gradoAcademicoService: GradoAcademicoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gradoAcademico }) => {
      this.updateForm(gradoAcademico);
    });
  }

  updateForm(gradoAcademico: IGradoAcademico): void {
    this.editForm.patchValue({
      id: gradoAcademico.id,
      descripcion: gradoAcademico.descripcion
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gradoAcademico = this.createFromForm();
    if (gradoAcademico.id !== undefined) {
      this.subscribeToSaveResponse(this.gradoAcademicoService.update(gradoAcademico));
    } else {
      this.subscribeToSaveResponse(this.gradoAcademicoService.create(gradoAcademico));
    }
  }

  private createFromForm(): IGradoAcademico {
    return {
      ...new GradoAcademico(),
      id: this.editForm.get(['id'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGradoAcademico>>): void {
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
