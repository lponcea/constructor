import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ContentBlocksService {
  // Objeto que contiene los bloques de contenido con toda su información.
  // En un futuro se obtendrá de la base de datos.
  contentBlocks: Array<any>;

  constructor() {
    this.contentBlocks = [];
  }

  // Devuelve el arreglo de objetos con todos lso bloques de contenido.
  getContentBlocks(): Array<any> {
    return this.contentBlocks;
  }

  /*
   * Busca y devuelve el bloque de contenido con el id recibido.
   * @param idContentBlock - Id del bloque de contenido a buscar.
   */
  getContentBlockById(idContentBlock: number): Object {
    let contentBlockFound = {};
    for (const contentBlock of this.contentBlocks) {
      if (contentBlock.id === idContentBlock) {
        contentBlockFound = contentBlock;
        break;
      }
    }
    return contentBlockFound;
  }

  /*
   * Genera un nuevo bloque de contenido con los parámetros por defecto.
   */
  createContentBlock(contentBlock: any): void {
    this.contentBlocks.push(contentBlock.selectedTemplate);
  }

  /*
   * Elimina el bloque de contenido correspondiente al id recibido.
   */
  deleteContentBlock(idBloquecontenido: number): void {
    for (let i = 0; i < this.contentBlocks.length; i++) {
      if (this.contentBlocks[i].id === idBloquecontenido) {
        this.contentBlocks.splice(i, 1);
      }
    }
  }
}
