import { IColaborador } from 'app/shared/model/colaborador.model';

export interface IRolColaborador {
  id?: number;
  descripcion?: string;
  colaboradors?: IColaborador[];
}

export class RolColaborador implements IRolColaborador {
  constructor(public id?: number, public descripcion?: string, public colaboradors?: IColaborador[]) {}
}
