import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ConstructorTestModule } from '../../../test.module';
import { AsignaturaUpdateComponent } from 'app/entities/asignatura/asignatura-update.component';
import { AsignaturaService } from 'app/entities/asignatura/asignatura.service';
import { Asignatura } from 'app/shared/model/asignatura.model';

describe('Component Tests', () => {
  describe('Asignatura Management Update Component', () => {
    let comp: AsignaturaUpdateComponent;
    let fixture: ComponentFixture<AsignaturaUpdateComponent>;
    let service: AsignaturaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ConstructorTestModule],
        declarations: [AsignaturaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AsignaturaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AsignaturaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AsignaturaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Asignatura(123);
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
        const entity = new Asignatura();
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
