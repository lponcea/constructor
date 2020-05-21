import { TipoNivelJerarquico } from './enumerations/tipo-nivel-jerarquico.model';
import { IBloqueComponentes } from './bloque-componentes.model';
import { ICurso } from './curso.model';

export interface INivelJerarquico {
  id?: number;
  nombre?: string;
  tipo?: TipoNivelJerarquico;
  informacionAdicional?: 0;
  bloquesComponentes?: IBloqueComponentes[];
  curso?: ICurso;
}

export class NivelJerarquico implements INivelJerarquico {
  public id?: number | undefined;
  public nombre?: string | undefined;
  public tipo?: TipoNivelJerarquico;
  public informacionAdicional?: 0;
  public bloquesComponentes?: IBloqueComponentes[];
  public curso?: ICurso;
}
