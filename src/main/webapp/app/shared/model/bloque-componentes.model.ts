import { Componente } from './componente.model';

export interface IBloqueComponentes {
  id?: number;
  tipoBloqueComponentes?: number;
  orden?: number;
  componentes?: Componente[];
}

export class BloqueComponentes implements IBloqueComponentes {
  constructor(public id?: number, public tipoBloqueComponentes?: number, public orden?: number, public componentes?: Componente[]) {}
}
