import { ICurso } from 'app/shared/model/curso.model';

export interface ICategoria {
  id?: number;
  descripcion?: string;
  cursos?: ICurso[];
}

export class Categoria implements ICategoria {
  constructor(public id?: number, public descripcion?: string, public cursos?: ICurso[]) {}
}
