import { Injectable } from '@angular/core';
import { HttpResponse, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { IRolesColaboradores } from 'app/shared/model/roles-colaboraderes.model';
import { createRequestOption } from 'app/shared/util/request-util';

type EntityArrayResponseType = HttpResponse<IRolesColaboradores[]>;

@Injectable({
  providedIn: 'root'
})
export class RolesColaboradoresService {
  public resourceUrl = SERVER_API_URL + 'api/roles-colaboradores';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRolesColaboradores[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
