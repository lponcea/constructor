import { Moment } from 'moment';
import { ICurso } from 'app/shared/model/curso.model';
import { IColaborador } from 'app/shared/model/colaborador.model';
import { IEditorial } from 'app/shared/model/editorial.model';
import { IRolesColaboradores } from './roles-colaboraderes.model';

export interface IFicha {
  id?: number;
  descripcion?: string;
  fechaCreacion?: Moment;
  curso?: ICurso;
  colaboradors?: IColaborador[];
  editorial?: IEditorial;
  creditosEditoriales?: IRolesColaboradores[];
}

export class Ficha implements IFicha {
  constructor(
    public id?: number,
    public descripcion?: string,
    public fechaCreacion?: Moment,
    public curso?: ICurso,
    public colaboradors?: IColaborador[],
    public editorial?: IEditorial,
    public creditosEditoriales?: IRolesColaboradores[]
  ) {}
}
