import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INumeroGrado } from 'app/shared/model/numero-grado.model';

@Component({
  selector: 'jhi-numero-grado-detail',
  templateUrl: './numero-grado-detail.component.html'
})
export class NumeroGradoDetailComponent implements OnInit {
  numeroGrado: INumeroGrado | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ numeroGrado }) => {
      this.numeroGrado = numeroGrado;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
