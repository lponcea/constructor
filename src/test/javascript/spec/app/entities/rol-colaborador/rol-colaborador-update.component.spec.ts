import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ConstructorTestModule } from '../../../test.module';
import { RolColaboradorUpdateComponent } from 'app/entities/rol-colaborador/rol-colaborador-update.component';
import { RolColaboradorService } from 'app/entities/rol-colaborador/rol-colaborador.service';
import { RolColaborador } from 'app/shared/model/rol-colaborador.model';

describe('Component Tests', () => {
  describe('RolColaborador Management Update Component', () => {
    let comp: RolColaboradorUpdateComponent;
    let fixture: ComponentFixture<RolColaboradorUpdateComponent>;
    let service: RolColaboradorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ConstructorTestModule],
        declarations: [RolColaboradorUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(RolColaboradorUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RolColaboradorUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RolColaboradorService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RolColaborador(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new RolColaborador();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
