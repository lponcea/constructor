import { Injectable } from '@angular/core';
import { HttpResponse, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITipoBloqueComponentes } from 'app/shared/model/tipo-bloque-componentes.model';

type EntityArrayResponseType = HttpResponse<ITipoBloqueComponentes[]>;

@Injectable({
  providedIn: 'root'
})
export class TipoComponenteService {
  public resourceUrl = SERVER_API_URL + 'api/tipo-bloque-componente';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITipoBloqueComponentes[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
