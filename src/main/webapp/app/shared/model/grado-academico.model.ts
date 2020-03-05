import { INumeroGrado } from 'app/shared/model/numero-grado.model';

export interface IGradoAcademico {
  id?: number;
  descripcion?: string;
  numeroGrados?: INumeroGrado[];
}

export class GradoAcademico implements IGradoAcademico {
  constructor(public id?: number, public descripcion?: string, public numeroGrados?: INumeroGrado[]) {}
}
