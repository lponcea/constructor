import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ConstructorTestModule } from '../../../test.module';
import { NumeroGradoDetailComponent } from 'app/entities/numero-grado/numero-grado-detail.component';
import { NumeroGrado } from 'app/shared/model/numero-grado.model';

describe('Component Tests', () => {
  describe('NumeroGrado Management Detail Component', () => {
    let comp: NumeroGradoDetailComponent;
    let fixture: ComponentFixture<NumeroGradoDetailComponent>;
    const route = ({ data: of({ numeroGrado: new NumeroGrado(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ConstructorTestModule],
        declarations: [NumeroGradoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NumeroGradoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NumeroGradoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load numeroGrado on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.numeroGrado).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
