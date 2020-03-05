import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFicha } from 'app/shared/model/ficha.model';

type EntityResponseType = HttpResponse<IFicha>;
type EntityArrayResponseType = HttpResponse<IFicha[]>;

@Injectable({ providedIn: 'root' })
export class FichaService {
  public resourceUrl = SERVER_API_URL + 'api/fichas';

  constructor(protected http: HttpClient) {}

  create(ficha: IFicha): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficha);
    return this.http
      .post<IFicha>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(ficha: IFicha): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficha);
    return this.http
      .put<IFicha>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFicha>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFicha[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(ficha: IFicha): IFicha {
    const copy: IFicha = Object.assign({}, ficha, {
      fechaCreacion: ficha.fechaCreacion && ficha.fechaCreacion.isValid() ? ficha.fechaCreacion.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaCreacion = res.body.fechaCreacion ? moment(res.body.fechaCreacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((ficha: IFicha) => {
        ficha.fechaCreacion = ficha.fechaCreacion ? moment(ficha.fechaCreacion) : undefined;
      });
    }
    return res;
  }
}
