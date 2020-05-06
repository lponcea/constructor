import { IPhoneNumber } from './phone-number.model';

export interface ICountry {
  id?: number;
  nombre?: string;
  name?: string;
  iso3?: string;
  phoneCode?: string;
  phoneNumbers?: IPhoneNumber[];
}

export class Country implements ICountry {
  constructor(
    public id?: number,
    public nombre?: string,
    public name?: string,
    public iso3?: string,
    public phoneCode?: string,
    public phoneNumbers?: IPhoneNumber[]
  ) {}
}
