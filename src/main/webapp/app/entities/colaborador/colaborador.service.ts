import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IColaborador } from 'app/shared/model/colaborador.model';

type EntityResponseType = HttpResponse<IColaborador>;
type EntityArrayResponseType = HttpResponse<IColaborador[]>;

@Injectable({ providedIn: 'root' })
export class ColaboradorService {
  public resourceUrl = SERVER_API_URL + 'api/colaboradors';

  constructor(protected http: HttpClient) {}

  create(colaborador: IColaborador): Observable<EntityResponseType> {
    return this.http.post<IColaborador>(this.resourceUrl, colaborador, { observe: 'response' });
  }

  update(colaborador: IColaborador): Observable<EntityResponseType> {
    return this.http.put<IColaborador>(this.resourceUrl, colaborador, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IColaborador>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IColaborador[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
