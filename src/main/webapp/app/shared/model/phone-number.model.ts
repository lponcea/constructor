import { IUser } from 'app/core/user/user.model';
import { ICountry } from './country.model';

export interface IPhoneNumber {
  id?: number;
  phoneNumber?: number;
  user?: IUser;
  country?: ICountry;
}

export class PhoneNumber implements IPhoneNumber {
  constructor(public id?: number, public phoneNumber?: number, public user?: IUser, public country?: ICountry) {}
}
