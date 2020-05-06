import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ICountry } from 'app/shared/model/country.model';
import { HttpResponse, HttpClient } from '@angular/common/http';
import { createRequestOption } from 'app/shared/util/request-util';
import { SERVER_API_URL } from 'app/app.constants';

type EntityArrayResponseType = HttpResponse<ICountry[]>;

@Injectable({
  providedIn: 'root'
})
export class CountryService {
  public resourceUrl = SERVER_API_URL + 'api/country';

  constructor(protected http: HttpClient) {}

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICountry[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
