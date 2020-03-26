import { IRolColaborador } from 'app/shared/model/rol-colaborador.model';
import { IFicha } from 'app/shared/model/ficha.model';

export interface IColaborador {
  id?: number;
  nombres?: string;
  apellido1?: string;
  apellido2?: string;
  rolColaborador?: IRolColaborador;
  fichas?: IFicha[];
}

export class Colaborador implements IColaborador {
  constructor(
    public id?: number,
    public nombres?: string,
    public apellido1?: string,
    public apellido2?: string,
    public rolColaborador?: IRolColaborador,
    public fichas?: IFicha[]
  ) {}
}
