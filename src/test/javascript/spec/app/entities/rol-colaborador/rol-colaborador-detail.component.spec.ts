import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ConstructorTestModule } from '../../../test.module';
import { RolColaboradorDetailComponent } from 'app/entities/rol-colaborador/rol-colaborador-detail.component';
import { RolColaborador } from 'app/shared/model/rol-colaborador.model';

describe('Component Tests', () => {
  describe('RolColaborador Management Detail Component', () => {
    let comp: RolColaboradorDetailComponent;
    let fixture: ComponentFixture<RolColaboradorDetailComponent>;
    const route = ({ data: of({ rolColaborador: new RolColaborador(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ConstructorTestModule],
        declarations: [RolColaboradorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RolColaboradorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RolColaboradorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load rolColaborador on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rolColaborador).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
