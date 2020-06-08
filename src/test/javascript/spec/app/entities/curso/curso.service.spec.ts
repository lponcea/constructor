import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CursoService } from 'app/entities/curso/curso.service';
import { ICurso, Curso } from 'app/shared/model/curso.model';
import { ModoDistribucion } from 'app/shared/model/enumerations/modo-distribucion.model';
import { EtapaEditorial } from 'app/shared/model/enumerations/etapa-editorial.model';

describe('Service Tests', () => {
  describe('Curso Service', () => {
    let injector: TestBed;
    let service: CursoService;
    let httpMock: HttpTestingController;
    let elemDefault: ICurso;
    let expectedResult: ICurso | ICurso[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CursoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Curso(
        0,
        'AAAAAAA',
        'AAAAAAA',
        ModoDistribucion.PAGO,
        EtapaEditorial.EDICION,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fechaCreacion: currentDate.format(DATE_FORMAT),
            fechaCreacionSys: currentDate.format(DATE_FORMAT),
            fechaPublicacion: currentDate.format(DATE_FORMAT),
            fechaPublicacionSys: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Curso', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaCreacion: currentDate.format(DATE_FORMAT),
            fechaCreacionSys: currentDate.format(DATE_FORMAT),
            fechaPublicacion: currentDate.format(DATE_FORMAT),
            fechaPublicacionSys: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaCreacion: currentDate,
            fechaCreacionSys: currentDate,
            fechaPublicacion: currentDate,
            fechaPublicacionSys: currentDate
          },
          returnedFromService
        );
        service
          .create(new Curso(), undefined)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Curso', () => {
        const returnedFromService = Object.assign(
          {
            titulo: 'BBBBBB',
            descripcion: 'BBBBBB',
            modoDistribucion: 'BBBBBB',
            etapaEditorial: 'BBBBBB',
            fechaCreacion: currentDate.format(DATE_FORMAT),
            fechaCreacionSys: currentDate.format(DATE_FORMAT),
            fechaPublicacion: currentDate.format(DATE_FORMAT),
            fechaPublicacionSys: currentDate.format(DATE_FORMAT),
            numeroEdicion: 'BBBBBB',
            versionStr: 'BBBBBB',
            palabraClave: 'BBBBBB',
            resumenContenido: 'BBBBBB',
            clave: 'BBBBBB',
            estatus: 'BBBBBB',
            portadaUrl: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaCreacion: currentDate,
            fechaCreacionSys: currentDate,
            fechaPublicacion: currentDate,
            fechaPublicacionSys: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Curso', () => {
        const returnedFromService = Object.assign(
          {
            titulo: 'BBBBBB',
            descripcion: 'BBBBBB',
            modoDistribucion: 'BBBBBB',
            etapaEditorial: 'BBBBBB',
            fechaCreacion: currentDate.format(DATE_FORMAT),
            fechaCreacionSys: currentDate.format(DATE_FORMAT),
            fechaPublicacion: currentDate.format(DATE_FORMAT),
            fechaPublicacionSys: currentDate.format(DATE_FORMAT),
            numeroEdicion: 'BBBBBB',
            versionStr: 'BBBBBB',
            palabraClave: 'BBBBBB',
            resumenContenido: 'BBBBBB',
            clave: 'BBBBBB',
            estatus: 'BBBBBB',
            portadaUrl: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaCreacion: currentDate,
            fechaCreacionSys: currentDate,
            fechaPublicacion: currentDate,
            fechaPublicacionSys: currentDate
          },
          returnedFromService
        );
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Curso', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
