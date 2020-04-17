import { IColaborador } from './colaborador.model';
import { IRolColaborador } from './rol-colaborador.model';

export interface IRolesColaboradores {
  id?: number;
  colaborador?: IColaborador;
  rolColaborador?: IRolColaborador;
}

export class RolesColaboradores implements IRolesColaboradores {
  constructor(public id?: number, public colaborador?: IColaborador, public rolColaborador?: IRolColaborador) {}
}
