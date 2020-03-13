import { Component, OnInit, OnDestroy, AfterContentInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { CursoService } from './../entities/curso/curso.service';
import { HttpResponse, HttpHeaders } from '@angular/common/http';
import { ICurso } from 'app/shared/model/curso.model';
import { FileUploadService } from 'app/services/file-upload.service';
import { SafeUrl, DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit, OnDestroy, AfterContentInit {
  account: Account | null = null;
  authSubscription?: Subscription;
  coverPaths = [];
  /*
  cursos = [
    {
      titulo: 'Español',
      portadaPath: ''
    },
    {
      titulo: 'Matemáticas',
      portadaPath: ''
    },
    {
      titulo: 'Geografía',
      portadaPath: ''
    },
    {
      titulo: 'Física',
      portadaPath: ''
    },
    {
      titulo: 'Test',
      portadaPath: ''
    },
    {
      titulo: 'Historia',
      portadaPath: ''
    }
  ];
  */
  cursos: any = [];
  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private cursoService: CursoService,
    private fileUploadService: FileUploadService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {}

  protected onQuerySuccess(data: ICurso[] | null, headers: HttpHeaders): void {
    this.cursos = data ? data : [];
  }

  ngAfterContentInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.cursoService.query().subscribe(
      (res: HttpResponse<ICurso[]>) => this.onQuerySuccess(res.body, res.headers),
      () => this.onQueryError()
    );
  }

  protected onQueryError(): void {}

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  deleteCourse(id: number): void {
    this.cursoService.delete(id).subscribe(() => {
      this.cursos.splice(this.findElementById(this.cursos, id), 1);
    });
  }

  protected onDeleteSuccess(data: ICurso[] | null, headers: HttpHeaders): void {
    this.cursos = data ? data : [];
  }

  protected onDeleteError(): void {}

  findElementById(objectArray: any, id: number): number {
    let foundIndex = -1;
    objectArray.forEach((value: any, index: number) => {
      if (value.id === id) {
        foundIndex = index;
      }
    });
    return foundIndex;
  }

  private getCover(path: string): void {
    /*
    this.fileUploadService.getFile(path).subscribe(data => {
      console.error(data);
    });
    */
  }
}

/* 
    let objectUrl: any;
    this.fileUploadService.getFile(path).subscribe(data => {
      objectUrl = URL.createObjectURL(data.body);      
    });
    console.error(this.sanitizer.bypassSecurityTrustUrl(objectUrl));    
    return this.sanitizer.bypassSecurityTrustUrl(objectUrl);
    */
