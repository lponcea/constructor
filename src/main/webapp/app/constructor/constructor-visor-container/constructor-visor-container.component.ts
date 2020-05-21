import { Component, OnInit } from '@angular/core';
import { ContentBlocksService } from 'app/services/content-blocks.service';
import { Subscription, Observable } from 'rxjs';
import { IBloqueComponentes, BloqueComponentes } from 'app/shared/model/bloque-componentes.model';
import { ITipoBloqueComponentes, TipoBloqueComponentes } from 'app/shared/model/tipo-bloque-componentes.model';
import { IComponente, Componente } from 'app/shared/model/componente.model';
import { NivelJerarquico, INivelJerarquico } from 'app/shared/model/nivel-jerarquico.model';
import { NivelJerarquicoService } from 'app/entities/nivel-jerarquico/nivel-jerarquico.service';
import { HttpResponse } from '@angular/common/http';

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

  constructor(private contentBlocksService: ContentBlocksService, private nivelJerarquicoService: NivelJerarquicoService) {
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

  onUpdateBlock($event: Event, index: number): void {
    if (this.contentBlocks[index]) {
      if (this.contentBlocks[index]!.componentes![$event['componentIndex']]) {
        switch ($event['type']) {
          case 'text': {
            this.contentBlocks[index]!.componentes![$event['componentIndex']].contenido = $event['newValue'];
          }
        }
      }
    }
  }

  save(): void {
    const curso = {
      id: 1
    };
    const nivel: NivelJerarquico = {
      bloquesComponentes: this.contentBlocks,
      curso
    };
    this.subscribeToSaveResponse(this.nivelJerarquicoService.create(nivel));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INivelJerarquico>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {}

  protected onSaveError(): void {}

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
