import { Moment } from 'moment';
import { ICurso } from 'app/shared/model/curso.model';

export interface IVersion {
  id?: number;
  version?: number;
  comentario?: string;
  fechaVersion?: Moment;
  cursos?: ICurso[];
}

export class Version implements IVersion {
  constructor(
    public id?: number,
    public version?: number,
    public comentario?: string,
    public fechaVersion?: Moment,
    public cursos?: ICurso[]
  ) {}
}
