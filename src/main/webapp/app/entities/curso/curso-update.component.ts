import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
// import * as moment from 'moment';

import { ICurso, Curso } from 'app/shared/model/curso.model';
import { CursoService } from './curso.service';
import { IModalidad } from 'app/shared/model/modalidad.model';
import { ModalidadService } from 'app/entities/modalidad/modalidad.service';
import { IVersion } from 'app/shared/model/version.model';
import { VersionService } from 'app/entities/version/version.service';
import { ICategoria } from 'app/shared/model/categoria.model';
import { CategoriaService } from 'app/entities/categoria/categoria.service';
import { IAsignatura } from 'app/shared/model/asignatura.model';
import { AsignaturaService } from 'app/entities/asignatura/asignatura.service';
import { INumeroGrado } from 'app/shared/model/numero-grado.model';
import { NumeroGradoService } from 'app/entities/numero-grado/numero-grado.service';
import { IGradoAcademico } from 'app/shared/model/grado-academico.model';
import { GradoAcademicoService } from 'app/entities/grado-academico/grado-academico.service';
import { CourseConfigurationService } from 'app/services/course-configuration.service';
import { FileUploadService } from 'app/services/file-upload.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { FichaUpdateComponent } from '../ficha/ficha-update.component';

import { JhiEventManager, JhiAlertService, JhiAlert, JhiEventWithContent } from 'ng-jhipster';

type SelectableEntity = IModalidad | IVersion | ICategoria | IAsignatura | INumeroGrado | IGradoAcademico;

@Component({
  selector: 'jhi-curso-update',
  templateUrl: './curso-update.component.html'
})
export class CursoUpdateComponent implements OnInit {
  selectedTabIndex = 0;
  isSaving = false;
  modalidads: IModalidad[] = [];
  versions: IVersion[] = [];
  categorias: ICategoria[] = [];
  asignaturas: IAsignatura[] = [];
  gradoAcademicos: IGradoAcademico[] = [];
  numerogrados: INumeroGrado[] = [];
  fechaCreacionDp: any;
  fechaCreacionSysDp: any;
  fechaPublicacionDp: any;
  fechaPublicacionSysDp: any;
  maxiCoverSize = 50000000;
  allowedFileTypes: any = ['image/jpg', 'image/jpeg', 'image/png'];
  showUploadButton = false;
  alerts: JhiAlert[] = [];

  changeImage = false;
  selectedFiles = FileList;
  currentFileUpload: File = this.selectedFiles[0];
  coverPath: SafeUrl = '';
  portadaUrl = '';

  editForm = this.fb.group({
    id: [],
    titulo: [],
    descripcion: [],
    modoDistribucion: [],
    etapaEditorial: [],
    fechaCreacion: [],
    fechaCreacionSys: [],
    fechaPublicacion: [],
    fechaPublicacionSys: [],
    numeroEdicion: [],
    versionStr: [],
    palabraClave: [],
    resumenContenido: [],
    clave: [],
    estatus: [],
    portadaUrl: [],
    modalidad: [],
    version: [],
    categoria: [],
    asignatura: [],
    gradoAcademico: [],
    numeroGrado: []
  });
  subscription: any;
  @ViewChild(FichaUpdateComponent, { static: false }) fichaUpdateComponent!: FichaUpdateComponent;

  constructor(
    protected cursoService: CursoService,
    protected modalidadService: ModalidadService,
    protected versionService: VersionService,
    protected categoriaService: CategoriaService,
    protected asignaturaService: AsignaturaService,
    protected numeroGradoService: NumeroGradoService,
    protected gradoAcademicoService: GradoAcademicoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private courseConfigurationService: CourseConfigurationService,
    private fileUploadService: FileUploadService,
    private sanitizer: DomSanitizer,
    private alertService: JhiAlertService,
    private eventManager: JhiEventManager
  ) {
    this.subscription = this.courseConfigurationService.getSelectedTab().subscribe(selectedTab => {
      if (selectedTab) {
        this.selectedTabIndex = selectedTab.selectedTab;
      }
    });
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ curso }) => {
      this.updateForm(curso);

      this.modalidadService
        .query()
        .pipe(
          map((res: HttpResponse<IModalidad[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IModalidad[]) => (this.modalidads = resBody));

      this.versionService
        .query()
        .pipe(
          map((res: HttpResponse<IVersion[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IVersion[]) => (this.versions = resBody));

      this.categoriaService
        .query()
        .pipe(
          map((res: HttpResponse<ICategoria[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICategoria[]) => (this.categorias = resBody));

      this.asignaturaService
        .query()
        .pipe(
          map((res: HttpResponse<IAsignatura[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IAsignatura[]) => (this.asignaturas = resBody));

      this.gradoAcademicoService
        .query()
        .pipe(
          map((res: HttpResponse<IGradoAcademico[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: INumeroGrado[]) => (this.gradoAcademicos = resBody));
    });
  }

  updateForm(curso: ICurso): void {
    if (curso === undefined) {
      return;
    }
    this.editForm.patchValue({
      id: curso.id,
      titulo: curso.titulo,
      descripcion: curso.descripcion,
      modoDistribucion: curso.modoDistribucion,
      etapaEditorial: curso.etapaEditorial,
      fechaCreacion: curso.fechaCreacion,
      fechaCreacionSys: curso.fechaCreacionSys,
      fechaPublicacion: curso.fechaPublicacion,
      fechaPublicacionSys: curso.fechaPublicacionSys,
      numeroEdicion: curso.numeroEdicion,
      versionStr: curso.versionStr,
      palabraClave: curso.palabraClave,
      resumenContenido: curso.resumenContenido,
      clave: curso.clave,
      estatus: curso.estatus,
      portadaUrl: curso.portadaUrl,
      modalidad: curso.modalidad,
      version: curso.version,
      categoria: curso.categoria,
      asignatura: curso.asignatura,
      numeroGrado: curso.numeroGrado
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const curso = this.createFromForm();
    const ficha = this.fichaUpdateComponent.createFromForm();
    const cursoFicha = {
      curso,
      ficha
    };
    if (curso.titulo === '' || curso.titulo === null || curso.titulo === undefined) {
      this.eventManager.broadcast(
        new JhiEventWithContent('constructorApp.validationError', {
          message: 'constructorApp.curso.validations.title'
        })
      );
      return;
    }
    curso.portadaUrl = this.portadaUrl;
    if (curso.id !== undefined && curso.id !== null) {
      this.subscribeToSaveResponse(this.cursoService.update(cursoFicha));
    } else {
      this.subscribeToSaveResponse(this.cursoService.create(cursoFicha));
    }
  }

  private createFromForm(): ICurso {
    return {
      ...new Curso(),
      id: this.editForm.get(['id'])!.value,
      titulo: this.editForm.get(['titulo'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value,
      modoDistribucion: this.editForm.get(['modoDistribucion'])!.value,
      etapaEditorial: this.editForm.get(['etapaEditorial'])!.value,
      fechaCreacion: this.editForm.get(['fechaCreacion'])!.value,
      fechaCreacionSys: this.editForm.get(['fechaCreacionSys'])!.value,
      fechaPublicacion: this.editForm.get(['fechaPublicacion'])!.value,
      fechaPublicacionSys: this.editForm.get(['fechaPublicacionSys'])!.value,
      numeroEdicion: this.editForm.get(['numeroEdicion'])!.value,
      versionStr: this.editForm.get(['versionStr'])!.value,
      palabraClave: this.editForm.get(['palabraClave'])!.value,
      resumenContenido: this.editForm.get(['resumenContenido'])!.value,
      clave: this.editForm.get(['clave'])!.value,
      estatus: this.editForm.get(['estatus'])!.value,
      portadaUrl: this.editForm.get(['portadaUrl'])!.value,
      modalidad: this.editForm.get(['modalidad'])!.value,
      version: this.editForm.get(['version'])!.value,
      categoria: this.editForm.get(['categoria'])!.value,
      asignatura: this.editForm.get(['asignatura'])!.value,
      numeroGrado: this.editForm.get(['numeroGrado'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICurso>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  changeGracoAcademico(e: any): void {
    this.gradoAcademicoService.find(e.target.selectedIndex).subscribe(res => {
      if (res.body && res.body.numeroGrados) this.numerogrados = res.body.numeroGrados;
    });
  }

  change(): void {
    this.changeImage = true;
  }

  upload($event: any): void {
    $event.preventDefault();
    this.currentFileUpload = this.selectedFiles[0];
    this.fileUploadService.pushFileStorage(this.currentFileUpload).subscribe(event => {
      this.portadaUrl = event.path;
      if (event) {
        this.getCover(event.path);
        this.showUploadButton = false;
      }
    });
  }

  getCover(path: string): void {
    this.fileUploadService.getFile(path).subscribe(data => {
      const objectUrl = URL.createObjectURL(data.body);
      this.coverPath = this.sanitizer.bypassSecurityTrustUrl(objectUrl);
    });
  }

  selectFile(event: any): void {
    if (event.target.files.length) {
      if (event.target.files[0].size > this.maxiCoverSize) {
        this.eventManager.broadcast(
          new JhiEventWithContent('constructorApp.validationError', {
            message: 'constructorApp.curso.validations.fileSize'
          })
        );
        return;
      } else if (!this.allowedFileTypes.includes(event.target.files[0].type)) {
        this.eventManager.broadcast(
          new JhiEventWithContent('constructorApp.validationError', {
            message: 'constructorApp.curso.validations.fileType'
          })
        );
        return;
      } else {
        this.selectedFiles = event.target.files;
        this.showUploadButton = true;
        this.upload(event);
      }
    }
  }

  deteleCover(): void {}
}
