import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ConstructorTestModule } from '../../../test.module';
import { GradoAcademicoUpdateComponent } from 'app/entities/grado-academico/grado-academico-update.component';
import { GradoAcademicoService } from 'app/entities/grado-academico/grado-academico.service';
import { GradoAcademico } from 'app/shared/model/grado-academico.model';

describe('Component Tests', () => {
  describe('GradoAcademico Management Update Component', () => {
    let comp: GradoAcademicoUpdateComponent;
    let fixture: ComponentFixture<GradoAcademicoUpdateComponent>;
    let service: GradoAcademicoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ConstructorTestModule],
        declarations: [GradoAcademicoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GradoAcademicoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GradoAcademicoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GradoAcademicoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GradoAcademico(123);
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
        const entity = new GradoAcademico();
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
