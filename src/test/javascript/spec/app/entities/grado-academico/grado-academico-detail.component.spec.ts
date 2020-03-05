import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ConstructorTestModule } from '../../../test.module';
import { GradoAcademicoDetailComponent } from 'app/entities/grado-academico/grado-academico-detail.component';
import { GradoAcademico } from 'app/shared/model/grado-academico.model';

describe('Component Tests', () => {
  describe('GradoAcademico Management Detail Component', () => {
    let comp: GradoAcademicoDetailComponent;
    let fixture: ComponentFixture<GradoAcademicoDetailComponent>;
    const route = ({ data: of({ gradoAcademico: new GradoAcademico(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ConstructorTestModule],
        declarations: [GradoAcademicoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GradoAcademicoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GradoAcademicoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load gradoAcademico on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gradoAcademico).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
