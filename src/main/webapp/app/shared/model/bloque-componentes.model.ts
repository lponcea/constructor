import { Componente } from './componente.model';
import { ITipoBloqueComponentes } from './tipo-bloque-componentes.model';

export interface IBloqueComponentes {
  id?: number;
  tipoBloqueComponentes?: ITipoBloqueComponentes;
  orden?: number;
  componentes?: Componente[];
}

export class BloqueComponentes implements IBloqueComponentes {
  constructor(
    public id?: number,
    public tipoBloqueComponentes?: ITipoBloqueComponentes,
    public orden?: number,
    public componentes?: Componente[]
  ) {}
}
