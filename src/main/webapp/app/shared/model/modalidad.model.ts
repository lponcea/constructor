import { ICurso } from 'app/shared/model/curso.model';

export interface IModalidad {
  id?: number;
  descripcion?: string;
  cursos?: ICurso[];
}

export class Modalidad implements IModalidad {
  constructor(public id?: number, public descripcion?: string, public cursos?: ICurso[]) {}
}
