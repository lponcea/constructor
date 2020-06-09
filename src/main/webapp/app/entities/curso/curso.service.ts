import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICurso } from 'app/shared/model/curso.model';

type EntityResponseType = HttpResponse<ICurso>;
type EntityArrayResponseType = HttpResponse<ICurso[]>;

@Injectable({ providedIn: 'root' })
export class CursoService {
  public resourceUrl = SERVER_API_URL + 'api/cursos';
  public resourceUrlNewBook = SERVER_API_URL + 'api/curso-ficha';

  constructor(protected http: HttpClient) {}

  create(curso: any, cover?: File): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(curso);
    const form = new FormData();
    // const courseBlob = new Blob(JSON.stringify(copy), { type: "application/json"});
    form.append('course', JSON.stringify(copy));
    if (cover) {
      form.append('file', cover);
    }
    return this.http
      .post<ICurso>(this.resourceUrlNewBook, form, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(curso: any): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(curso);
    return this.http
      .put<ICurso>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICurso>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICurso[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(curso: ICurso): ICurso {
    const copy: ICurso = Object.assign({}, curso, {
      fechaCreacion: curso.fechaCreacion && curso.fechaCreacion.isValid() ? curso.fechaCreacion.format(DATE_FORMAT) : undefined,
      fechaCreacionSys: curso.fechaCreacionSys && curso.fechaCreacionSys.isValid() ? curso.fechaCreacionSys.format(DATE_FORMAT) : undefined,
      fechaPublicacion: curso.fechaPublicacion && curso.fechaPublicacion.isValid() ? curso.fechaPublicacion.format(DATE_FORMAT) : undefined,
      fechaPublicacionSys:
        curso.fechaPublicacionSys && curso.fechaPublicacionSys.isValid() ? curso.fechaPublicacionSys.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaCreacion = res.body.fechaCreacion ? moment(res.body.fechaCreacion) : undefined;
      res.body.fechaCreacionSys = res.body.fechaCreacionSys ? moment(res.body.fechaCreacionSys) : undefined;
      res.body.fechaPublicacion = res.body.fechaPublicacion ? moment(res.body.fechaPublicacion) : undefined;
      res.body.fechaPublicacionSys = res.body.fechaPublicacionSys ? moment(res.body.fechaPublicacionSys) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((curso: ICurso) => {
        curso.fechaCreacion = curso.fechaCreacion ? moment(curso.fechaCreacion) : undefined;
        curso.fechaCreacionSys = curso.fechaCreacionSys ? moment(curso.fechaCreacionSys) : undefined;
        curso.fechaPublicacion = curso.fechaPublicacion ? moment(curso.fechaPublicacion) : undefined;
        curso.fechaPublicacionSys = curso.fechaPublicacionSys ? moment(curso.fechaPublicacionSys) : undefined;
      });
    }
    return res;
  }
}
