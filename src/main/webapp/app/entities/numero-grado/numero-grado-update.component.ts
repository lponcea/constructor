import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { INumeroGrado, NumeroGrado } from 'app/shared/model/numero-grado.model';
import { NumeroGradoService } from './numero-grado.service';
import { IGradoAcademico } from 'app/shared/model/grado-academico.model';
import { GradoAcademicoService } from 'app/entities/grado-academico/grado-academico.service';

@Component({
  selector: 'jhi-numero-grado-update',
  templateUrl: './numero-grado-update.component.html'
})
export class NumeroGradoUpdateComponent implements OnInit {
  isSaving = false;

  gradoacademicos: IGradoAcademico[] = [];

  editForm = this.fb.group({
    id: [],
    descripcion: [],
    gradoAcademico: []
  });

  constructor(
    protected numeroGradoService: NumeroGradoService,
    protected gradoAcademicoService: GradoAcademicoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ numeroGrado }) => {
      this.updateForm(numeroGrado);

      this.gradoAcademicoService
        .query()
        .pipe(
          map((res: HttpResponse<IGradoAcademico[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IGradoAcademico[]) => (this.gradoacademicos = resBody));
    });
  }

  updateForm(numeroGrado: INumeroGrado): void {
    this.editForm.patchValue({
      id: numeroGrado.id,
      descripcion: numeroGrado.descripcion,
      gradoAcademico: numeroGrado.gradoAcademico
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const numeroGrado = this.createFromForm();
    if (numeroGrado.id !== undefined) {
      this.subscribeToSaveResponse(this.numeroGradoService.update(numeroGrado));
    } else {
      this.subscribeToSaveResponse(this.numeroGradoService.create(numeroGrado));
    }
  }

  private createFromForm(): INumeroGrado {
    return {
      ...new NumeroGrado(),
      id: this.editForm.get(['id'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value,
      gradoAcademico: this.editForm.get(['gradoAcademico'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INumeroGrado>>): void {
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

  trackById(index: number, item: IGradoAcademico): any {
    return item.id;
  }
}
