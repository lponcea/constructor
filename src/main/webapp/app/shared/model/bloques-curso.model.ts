import { IBloqueComponentes } from './bloque-componentes.model';

export interface IBloquesCurso {
  id?: number;
  bloqueComponentes?: IBloqueComponentes;
  orden?: number;
  mostrar?: number;
  indicadorOriginal?: number;
}

export class BloquesCurso implements IBloquesCurso {
  constructor(
    public id?: number,
    public bloqueComponentes?: IBloqueComponentes,
    public orden?: number,
    public mostrar?: number,
    public indicadorOriginal?: number
  ) {}
}
