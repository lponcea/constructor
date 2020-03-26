import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ConstructorTestModule } from '../../../test.module';
import { AsignaturaDetailComponent } from 'app/entities/asignatura/asignatura-detail.component';
import { Asignatura } from 'app/shared/model/asignatura.model';

describe('Component Tests', () => {
  describe('Asignatura Management Detail Component', () => {
    let comp: AsignaturaDetailComponent;
    let fixture: ComponentFixture<AsignaturaDetailComponent>;
    const route = ({ data: of({ asignatura: new Asignatura(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ConstructorTestModule],
        declarations: [AsignaturaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AsignaturaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AsignaturaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load asignatura on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.asignatura).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
