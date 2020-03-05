import { IFicha } from 'app/shared/model/ficha.model';

export interface IEditorial {
  id?: number;
  nombre?: string;
  descripcion?: string;
  direccion?: string;
  telefono?: string;
  fichas?: IFicha[];
}

export class Editorial implements IEditorial {
  constructor(
    public id?: number,
    public nombre?: string,
    public descripcion?: string,
    public direccion?: string,
    public telefono?: string,
    public fichas?: IFicha[]
  ) {}
}
