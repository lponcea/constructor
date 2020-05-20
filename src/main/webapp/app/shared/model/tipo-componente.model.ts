import { IComponente } from './componente.model';

export interface ITipoComponente {
  id?: number;
  nombre?: string;
  componentes?: IComponente[];
}

export class TipoComponente implements ITipoComponente {
  constructor(public id?: number, public nombre?: string, public componentes?: IComponente[]) {}
}
