import { IBloqueComponentes } from './bloque-componentes.model';
import { ITipoComponente } from './tipo-componente.model';

export interface IComponente {
  id?: number;
  contenido?: string;
  version?: number;
  tipoComponente?: ITipoComponente;
  bloqueComponente?: IBloqueComponentes;
  orden?: number;
}

export class Componente implements IComponente {
  constructor(
    public id?: number,
    public contenido?: string,
    public version?: number,
    public tipoComponente?: ITipoComponente,
    public bloqueComponente?: IBloqueComponentes,
    public orden?: number
  ) {}
}
