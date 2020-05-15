import { Component, OnInit, AfterContentInit } from '@angular/core';
import { BlockSelectionService } from 'app/services/block-selection.service';
import { ContentBlocksService } from 'app/services/content-blocks.service';
import { NavigationControlsService } from '../../services/navigation-controls.service';
import { Subscription } from 'rxjs';
import { IBloqueComponentes } from 'app/shared/model/bloque-componentes.model';

@Component({
  selector: 'jhi-constructor-filmstrip',
  templateUrl: './constructor-filmstrip.component.html',
  styleUrls: ['./constructor-filmstrip.component.scss']
})
export class ConstructorFilmstripComponent implements OnInit, AfterContentInit {
  selectedContentBlockIndex = -1;
  contentBlocks: IBloqueComponentes[];
  selectedTemplateType = '';
  subscription: Subscription;

  imagePaths = [
    {
      name: 'title',
      contentBlockType: 'titulo',
      path: '../../../content/images/ab1.png',
      tags: 'text'
    },
    {
      name: 'text',
      contentBlockType: 'texto',
      path: '../../../content/images/ab2.png',
      tags: 'text'
    },
    {
      name: 'image',
      contentBlockType: 'imagen',
      path: '../../../content/images/ab3.png',
      tags: 'image'
    },
    {
      name: 'image_text',
      contentBlockType: 'imagen_texto',
      path: '../../../content/images/ab4.png',
      tags: 'image text'
    }
  ];

  constructor(
    private contentBlocksService: ContentBlocksService,
    private blockSelectionService: BlockSelectionService,
    private navigationControlsService: NavigationControlsService
  ) {
    this.contentBlocks = [];
    this.subscription = this.contentBlocksService.getContentBlocks().subscribe(contentBlocks => {
      if (contentBlocks) {
        this.contentBlocks = contentBlocks;
      }
    });
  }

  ngOnInit(): void {
    // this.contentBlocks = this.contentBlocksService.getContentBlocks();
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
  getContentBlockImage(path: string): string {
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
  deleteContentBlock(index: number): void {
    this.contentBlocksService.setIndexBlockToDelete(index);
  }
}
