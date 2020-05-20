import { Component, OnInit } from '@angular/core';
import { ContentBlocksService } from 'app/services/content-blocks.service';
import { Subscription } from 'rxjs';
import { IBloqueComponentes, BloqueComponentes } from 'app/shared/model/bloque-componentes.model';
import { ITipoBloqueComponentes, TipoBloqueComponentes } from 'app/shared/model/tipo-bloque-componentes.model';
import { IComponente, Componente } from 'app/shared/model/componente.model';

@Component({
  selector: 'jhi-constructor-visor-container',
  templateUrl: './constructor-visor-container.component.html',
  styleUrls: ['./constructor-visor-container.component.scss']
})
export class ConstructorVisorContainerComponent implements OnInit {
  subscription: Subscription;
  selectedTemplateType = '';
  contentBlocks = Array<IBloqueComponentes>();
  tiposComponente = [
    {
      id: 1,
      nombre: 'text'
    },
    {
      id: 2,
      nombre: 'image'
    }
  ];

  constructor(private contentBlocksService: ContentBlocksService) {
    this.contentBlocks = [];
    this.subscription = this.contentBlocksService.getSelectedBlock().subscribe(selectedBlock => {
      if (selectedBlock !== undefined) {
        this.contentBlocks.push(this.createContentBlock(selectedBlock.selectedBlock));
        this.contentBlocksService.setContentBlocks(this.contentBlocks);
      }
    });
    this.subscription = this.contentBlocksService.getIndexBlockToDelete().subscribe(indexBlockToDelete => {
      if (indexBlockToDelete) {
        this.deleteContentBlock(indexBlockToDelete);
      }
    });
  }

  createContentBlock(selectedBlock: ITipoBloqueComponentes): IBloqueComponentes {
    const componentes = new Array<IComponente>();
    for (let i = 0; i < selectedBlock.tiposComponentes!.length; i++) {
      componentes.push(this.createComponent(selectedBlock.tiposComponentes![i]));
    }
    return {
      ...new BloqueComponentes(),
      tipoBloqueComponentes: selectedBlock,
      orden: this.determineNewBlockOrder(),
      componentes
    };
  }

  createComponent(componentBlockType: TipoBloqueComponentes): IComponente {
    return {
      ...new Componente(),
      contenido: 'Contenido de nuevo componente de tipo de bloque ' + componentBlockType.nombre,
      tipoComponente: componentBlockType
    };
  }

  determineNewBlockOrder(): number {
    return this.contentBlocks.length;
  }

  deleteContentBlock(index: number): void {
    if (index > -1) {
      this.contentBlocks.splice(index, 1);
      this.contentBlocksService.setContentBlocks(this.contentBlocks);
    }
  }

  ngOnInit(): void {}
}
