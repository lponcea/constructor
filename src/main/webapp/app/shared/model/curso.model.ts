import { Moment } from 'moment';
import { IModalidad } from 'app/shared/model/modalidad.model';
import { IVersion } from 'app/shared/model/version.model';
import { ICategoria } from 'app/shared/model/categoria.model';
import { IAsignatura } from 'app/shared/model/asignatura.model';
import { INumeroGrado } from 'app/shared/model/numero-grado.model';
import { ModoDistribucion } from 'app/shared/model/enumerations/modo-distribucion.model';
import { EtapaEditorial } from 'app/shared/model/enumerations/etapa-editorial.model';

export interface ICurso {
  id?: number;
  titulo?: string;
  descripcion?: string;
  modoDistribucion?: ModoDistribucion;
  etapaEditorial?: EtapaEditorial;
  fechaCreacion?: Moment;
  fechaCreacionSys?: Moment;
  fechaPublicacion?: Moment;
  fechaPublicacionSys?: Moment;
  numeroEdicion?: string;
  versionStr?: string;
  palabraClave?: string;
  resumenContenido?: string;
  clave?: string;
  estatus?: string;
  portadaUrl?: string;
  modalidad?: IModalidad;
  version?: IVersion;
  categoria?: ICategoria;
  asignatura?: IAsignatura;
  numeroGrado?: INumeroGrado;
}

export class Curso implements ICurso {
  constructor(
    public id?: number,
    public titulo?: string,
    public descripcion?: string,
    public modoDistribucion?: ModoDistribucion,
    public etapaEditorial?: EtapaEditorial,
    public fechaCreacion?: Moment,
    public fechaCreacionSys?: Moment,
    public fechaPublicacion?: Moment,
    public fechaPublicacionSys?: Moment,
    public numeroEdicion?: string,
    public versionStr?: string,
    public palabraClave?: string,
    public resumenContenido?: string,
    public clave?: string,
    public estatus?: string,
    public portadaUrl?: string,
    public modalidad?: IModalidad,
    public version?: IVersion,
    public categoria?: ICategoria,
    public asignatura?: IAsignatura,
    public numeroGrado?: INumeroGrado
  ) {}
}
