import { Component, OnInit, AfterContentInit } from '@angular/core';
import { BlockSelectionService } from 'app/services/block-selection.service';
import { ContentBlocksService } from 'app/services/content-blocks.service';
import { NavigationControlsService } from '../../services/navigation-controls.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'jhi-constructor-filmstrip',
  templateUrl: './constructor-filmstrip.component.html',
  styleUrls: ['./constructor-filmstrip.component.scss']
})
export class ConstructorFilmstripComponent implements OnInit, AfterContentInit {
  selectedContentBlockIndex = -1;
  contentBlocks: Array<Object> = [];
  selectedTemplateType = '';
  subscription: Subscription;

  imagePaths = [
    {
      contentBlockType: 'text_imagen_columnas',
      path: '../../../content/images/ab1.png'
    },
    {
      contentBlockType: 'imagen_texto_columnas',
      path: '../../../content/images/ab2.png'
    },
    {
      contentBlockType: 'imagen',
      path: '../../../content/images/ab3.png'
    },
    {
      contentBlockType: 'tres_columnas_imagen_texto_filas',
      path: '../../../content/images/ab4.png'
    }
  ];

  constructor(
    private contentBlocksService: ContentBlocksService,
    private blockSelectionService: BlockSelectionService,
    private navigationControlsService: NavigationControlsService
  ) {
    this.subscription = this.navigationControlsService.getSelectedTemplateType().subscribe(selectedTemplate => {
      if (selectedTemplate) {
        this.selectedTemplateType = selectedTemplate.selectedTemplateType;
        this.contentBlocksService.createContentBlock(selectedTemplate.selectedTemplateType);
      }
    });
  }

  ngOnInit(): void {
    this.contentBlocks = this.contentBlocksService.getContentBlocks();
  }

  /*
   * Selecciona el primer bloque de contenido al terminar de cargar la página.
   */
  ngAfterContentInit(): void {
    this.selectContentBlock(0);
  }

  /*
   * Obtiene la imagen para el fimrstrip de acuerdo con el tipo de bloque de contenido.
   */
  getContentBlockImage(contentBlockType: string): string {
    let path = '';
    for (const imagePath of this.imagePaths) {
      if (imagePath.contentBlockType === contentBlockType) {
        path = imagePath['path'];
      }
    }
    return path;
  }

  /*
  
  */
  selectContentBlock(selectedContentBlockIndex: number): void {
    this.selectedContentBlockIndex = selectedContentBlockIndex;
    // this.messageService.sendMessage(text);
  }

  /*
   * Crea un bloque de contenido en el servicio ContentBlocksService.
   */
  createContentBlock(): void {
    this.navigationControlsService.setOpenTemplateGallery(true);
  }

  /*
   * Elimina el bloque de contenido seleccionado en ContentBlocksService.
   */
  deleteContentBlock(idBloquecontenido: number): void {
    this.contentBlocksService.deleteContentBlock(idBloquecontenido);
  }
}
