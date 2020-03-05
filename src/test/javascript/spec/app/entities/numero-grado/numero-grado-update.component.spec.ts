import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ConstructorTestModule } from '../../../test.module';
import { NumeroGradoUpdateComponent } from 'app/entities/numero-grado/numero-grado-update.component';
import { NumeroGradoService } from 'app/entities/numero-grado/numero-grado.service';
import { NumeroGrado } from 'app/shared/model/numero-grado.model';

describe('Component Tests', () => {
  describe('NumeroGrado Management Update Component', () => {
    let comp: NumeroGradoUpdateComponent;
    let fixture: ComponentFixture<NumeroGradoUpdateComponent>;
    let service: NumeroGradoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ConstructorTestModule],
        declarations: [NumeroGradoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NumeroGradoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NumeroGradoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NumeroGradoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NumeroGrado(123);
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
        const entity = new NumeroGrado();
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
