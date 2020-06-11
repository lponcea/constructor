import { IBloqueComponentes } from './bloque-componentes.model';

export interface IBloquesCurso {
  id?: number;
  bloqueComponentes?: IBloqueComponentes;
  orden?: number;
  mostrar?: boolean;
  indicadorOriginal?: boolean;
}

export class BloquesCurso implements IBloquesCurso {
  constructor(
    public id?: number,
    public bloqueComponentes?: IBloqueComponentes,
    public orden?: number,
    public mostrar?: boolean,
    public indicadorOriginal?: boolean
  ) {}
}
