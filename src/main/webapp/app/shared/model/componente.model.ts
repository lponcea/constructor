import { IBloqueComponentes } from './bloque-componentes.model';
import { ITipoComponente } from './tipo-componente.model';
import { IContenido } from './contenido.model';

export interface IComponente {
  id?: number;
  contenido?: IContenido;
  version?: number;
  tipoComponente?: ITipoComponente;
  bloqueComponente?: IBloqueComponentes;
  orden?: number;
}

export class Componente implements IComponente {
  constructor(
    public id?: number,
    public contenido?: IContenido,
    public version?: number,
    public tipoComponente?: ITipoComponente,
    public bloqueComponente?: IBloqueComponentes,
    public orden?: number
  ) {}
}
