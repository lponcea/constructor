export interface IContenido {
  id?: number;
  contenido?: string;
}

export class Contenido implements IContenido {
  constructor(public id?: number, public contenido?: string) {}
}
