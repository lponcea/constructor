import { IUser } from 'app/core/user/user.model';
import { ICountry } from './country.model';

export interface IPhoneNumber {
  id?: number;
  phoneNumber?: string;
  user?: IUser;
  country?: ICountry;
}

export class PhoneNumber implements IPhoneNumber {
  constructor(public id?: number, public phoneNumber?: string, public user?: IUser, public country?: ICountry) {}
}
