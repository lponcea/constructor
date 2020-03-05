import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGradoAcademico } from 'app/shared/model/grado-academico.model';

type EntityResponseType = HttpResponse<IGradoAcademico>;
type EntityArrayResponseType = HttpResponse<IGradoAcademico[]>;

@Injectable({ providedIn: 'root' })
export class GradoAcademicoService {
  public resourceUrl = SERVER_API_URL + 'api/grado-academicos';

  constructor(protected http: HttpClient) {}

  create(gradoAcademico: IGradoAcademico): Observable<EntityResponseType> {
    return this.http.post<IGradoAcademico>(this.resourceUrl, gradoAcademico, { observe: 'response' });
  }

  update(gradoAcademico: IGradoAcademico): Observable<EntityResponseType> {
    return this.http.put<IGradoAcademico>(this.resourceUrl, gradoAcademico, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGradoAcademico>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGradoAcademico[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
