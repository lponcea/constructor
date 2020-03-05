import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IColaborador, Colaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from './colaborador.service';
import { IRolColaborador } from 'app/shared/model/rol-colaborador.model';
import { RolColaboradorService } from 'app/entities/rol-colaborador/rol-colaborador.service';

@Component({
  selector: 'jhi-colaborador-update',
  templateUrl: './colaborador-update.component.html'
})
export class ColaboradorUpdateComponent implements OnInit {
  isSaving = false;

  rolcolaboradors: IRolColaborador[] = [];

  editForm = this.fb.group({
    id: [],
    nombres: [],
    apellido1: [],
    apellido2: [],
    rolColaborador: []
  });

  constructor(
    protected colaboradorService: ColaboradorService,
    protected rolColaboradorService: RolColaboradorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ colaborador }) => {
      this.updateForm(colaborador);

      this.rolColaboradorService
        .query()
        .pipe(
          map((res: HttpResponse<IRolColaborador[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IRolColaborador[]) => (this.rolcolaboradors = resBody));
    });
  }

  updateForm(colaborador: IColaborador): void {
    this.editForm.patchValue({
      id: colaborador.id,
      nombres: colaborador.nombres,
      apellido1: colaborador.apellido1,
      apellido2: colaborador.apellido2,
      rolColaborador: colaborador.rolColaborador
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const colaborador = this.createFromForm();
    if (colaborador.id !== undefined) {
      this.subscribeToSaveResponse(this.colaboradorService.update(colaborador));
    } else {
      this.subscribeToSaveResponse(this.colaboradorService.create(colaborador));
    }
  }

  private createFromForm(): IColaborador {
    return {
      ...new Colaborador(),
      id: this.editForm.get(['id'])!.value,
      nombres: this.editForm.get(['nombres'])!.value,
      apellido1: this.editForm.get(['apellido1'])!.value,
      apellido2: this.editForm.get(['apellido2'])!.value,
      rolColaborador: this.editForm.get(['rolColaborador'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IColaborador>>): void {
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

  trackById(index: number, item: IRolColaborador): any {
    return item.id;
  }
}
