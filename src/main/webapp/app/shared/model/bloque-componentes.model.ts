import { ITipoBloqueComponentes } from './tipo-bloque-componentes.model';

export interface IBloqueComponentes {
  id?: number;
  tipoBloqueComponentes?: ITipoBloqueComponentes;
  orden?: number;
}

export class BloqueComponentes implements IBloqueComponentes {
  constructor(public id?: number, public tipoBloqueComponentes?: ITipoBloqueComponentes, public orden?: number) {}
}
