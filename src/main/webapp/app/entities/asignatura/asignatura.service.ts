import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAsignatura } from 'app/shared/model/asignatura.model';

type EntityResponseType = HttpResponse<IAsignatura>;
type EntityArrayResponseType = HttpResponse<IAsignatura[]>;

@Injectable({ providedIn: 'root' })
export class AsignaturaService {
  public resourceUrl = SERVER_API_URL + 'api/asignaturas';

  constructor(protected http: HttpClient) {}

  create(asignatura: IAsignatura): Observable<EntityResponseType> {
    return this.http.post<IAsignatura>(this.resourceUrl, asignatura, { observe: 'response' });
  }

  update(asignatura: IAsignatura): Observable<EntityResponseType> {
    return this.http.put<IAsignatura>(this.resourceUrl, asignatura, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAsignatura>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAsignatura[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
