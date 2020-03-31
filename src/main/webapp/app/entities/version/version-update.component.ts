import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVersion, Version } from 'app/shared/model/version.model';
import { VersionService } from './version.service';

@Component({
  selector: 'jhi-version-update',
  templateUrl: './version-update.component.html'
})
export class VersionUpdateComponent implements OnInit {
  isSaving = false;
  fechaVersionDp: any;

  editForm = this.fb.group({
    id: [],
    version: [],
    comentario: [],
    fechaVersion: []
  });

  constructor(protected versionService: VersionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ version }) => {
      this.updateForm(version);
    });
  }

  updateForm(version: IVersion): void {
    this.editForm.patchValue({
      id: version.id,
      version: version.version,
      comentario: version.comentario,
      fechaVersion: version.fechaVersion
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const version = this.createFromForm();
    if (version.id !== undefined) {
      this.subscribeToSaveResponse(this.versionService.update(version));
    } else {
      this.subscribeToSaveResponse(this.versionService.create(version));
    }
  }

  private createFromForm(): IVersion {
    return {
      ...new Version(),
      id: this.editForm.get(['id'])!.value,
      version: this.editForm.get(['version'])!.value,
      comentario: this.editForm.get(['comentario'])!.value,
      fechaVersion: this.editForm.get(['fechaVersion'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVersion>>): void {
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
