import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INumeroGrado } from 'app/shared/model/numero-grado.model';

type EntityResponseType = HttpResponse<INumeroGrado>;
type EntityArrayResponseType = HttpResponse<INumeroGrado[]>;

@Injectable({ providedIn: 'root' })
export class NumeroGradoService {
  public resourceUrl = SERVER_API_URL + 'api/numero-grados/';

  constructor(protected http: HttpClient) {}

  create(numeroGrado: INumeroGrado): Observable<EntityResponseType> {
    return this.http.post<INumeroGrado>(this.resourceUrl, numeroGrado, { observe: 'response' });
  }

  update(numeroGrado: INumeroGrado): Observable<EntityResponseType> {
    return this.http.put<INumeroGrado>(this.resourceUrl, numeroGrado, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INumeroGrado>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  filterByGradoAcademico(idGradoAcademico: number): Observable<EntityArrayResponseType> {
    return this.http.get<INumeroGrado[]>(this.resourceUrl + '/grado-academicos/' + idGradoAcademico, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INumeroGrado[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
