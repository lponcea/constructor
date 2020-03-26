import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRolColaborador } from 'app/shared/model/rol-colaborador.model';

@Component({
  selector: 'jhi-rol-colaborador-detail',
  templateUrl: './rol-colaborador-detail.component.html'
})
export class RolColaboradorDetailComponent implements OnInit {
  rolColaborador: IRolColaborador | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rolColaborador }) => {
      this.rolColaborador = rolColaborador;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
