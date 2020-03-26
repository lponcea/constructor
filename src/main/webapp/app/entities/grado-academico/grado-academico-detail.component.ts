import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGradoAcademico } from 'app/shared/model/grado-academico.model';

@Component({
  selector: 'jhi-grado-academico-detail',
  templateUrl: './grado-academico-detail.component.html'
})
export class GradoAcademicoDetailComponent implements OnInit {
  gradoAcademico: IGradoAcademico | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gradoAcademico }) => {
      this.gradoAcademico = gradoAcademico;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
