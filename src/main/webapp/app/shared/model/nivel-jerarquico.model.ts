import { TipoNivelJerarquico } from './enumerations/tipo-nivel-jerarquico.model';
import { IBloqueComponentes } from './bloque-componentes.model';

export interface INivelJerarquico {
  id?: number;
  nombre?: string;
  tipo?: TipoNivelJerarquico;
  informacionAdicional?: 0;
  bloquesComponentes?: IBloqueComponentes[];
  cursoId?: number;
  orden?: number;
}

export class NivelJerarquico implements INivelJerarquico {
  public id?: number | undefined;
  public nombre?: string | undefined;
  public tipo?: TipoNivelJerarquico;
  public informacionAdicional?: 0;
  public bloquesComponentes?: IBloqueComponentes[];
  public cursoId?: number;
  public orden?: number;
}
