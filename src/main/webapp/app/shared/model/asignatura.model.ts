import { ICurso } from 'app/shared/model/curso.model';

export interface IAsignatura {
  id?: number;
  descripcion?: string;
  cursos?: ICurso[];
}

export class Asignatura implements IAsignatura {
  constructor(public id?: number, public descripcion?: string, public cursos?: ICurso[]) {}
}
