import { Component, OnInit } from '@angular/core';
import { ContentBlocksService } from 'app/services/content-blocks.service';
import { Subscription } from 'rxjs';
import { IBloqueComponentes, BloqueComponentes } from 'app/shared/model/bloque-componentes.model';

@Component({
  selector: 'jhi-constructor-visor-container',
  templateUrl: './constructor-visor-container.component.html',
  styleUrls: ['./constructor-visor-container.component.scss']
})
export class ConstructorVisorContainerComponent implements OnInit {
  subscription: Subscription;
  selectedTemplateType = '';
  contentBlocks = Array<IBloqueComponentes>();

  constructor(private contentBlocksService: ContentBlocksService) {
    this.contentBlocks = [];
    this.subscription = this.contentBlocksService.getSelectedBlock().subscribe(selectedBlock => {
      if (selectedBlock !== undefined) {
        this.contentBlocks.push(this.createContentBlock(selectedBlock));
        this.contentBlocksService.setContentBlocks(this.contentBlocks);
      }
    });
    this.subscription = this.contentBlocksService.getIndexBlockToDelete().subscribe(indexBlockToDelete => {
      if (indexBlockToDelete) {
        this.deleteContentBlock(indexBlockToDelete);
      }
    });
  }

  createContentBlock(selectedBlock: any): IBloqueComponentes {
    return {
      ...new BloqueComponentes(),
      tipoBloqueComponentes: selectedBlock.selectedBlock,
      orden: this.determineNewBlockOrder()
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
