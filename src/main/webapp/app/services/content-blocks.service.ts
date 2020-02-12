import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ContentBlocksService {
  // Objeto que contiene los bloques de contenido con toda su información.
  // En un futuro se obtendrá de la base de datos.
  contentBlocks = [
    /*
    {
      title: "Portada",
      contentBlockType: "imagen"
    },
    {
      title: "Dos",
      contentBlockType: "text_imagen_columnas"
    },
    {
      title: "Tres",
      contentBlockType: "imagen_texto_columnas"
    },
    {
      title: "Cuatro",
      contentBlockType: "imagen"
    },
    */
    {
      id: 1,
      title: 'Bloque de ejemplo',
      contentBlockType: 'tres_columnas_imagen_texto_filas'
    }
  ];

  constructor() {}

  // Devuelve el arreglo de objetos con todos lso bloques de contenido.
  getContentBlocks(): Array<Object> {
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
  createContentBlock(contentBlockType: string): void {
    this.contentBlocks.push({
      id: this.contentBlocks.length + 1,
      title: 'Nuevo bloque',
      contentBlockType
    });
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
