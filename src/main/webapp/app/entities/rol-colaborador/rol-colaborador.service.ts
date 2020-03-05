import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRolColaborador } from 'app/shared/model/rol-colaborador.model';

type EntityResponseType = HttpResponse<IRolColaborador>;
type EntityArrayResponseType = HttpResponse<IRolColaborador[]>;

@Injectable({ providedIn: 'root' })
export class RolColaboradorService {
  public resourceUrl = SERVER_API_URL + 'api/rol-colaboradors';

  constructor(protected http: HttpClient) {}

  create(rolColaborador: IRolColaborador): Observable<EntityResponseType> {
    return this.http.post<IRolColaborador>(this.resourceUrl, rolColaborador, { observe: 'response' });
  }

  update(rolColaborador: IRolColaborador): Observable<EntityResponseType> {
    return this.http.put<IRolColaborador>(this.resourceUrl, rolColaborador, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRolColaborador>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRolColaborador[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
