import { Component, OnInit } from '@angular/core';
import { ContentBlocksService } from 'app/services/content-blocks.service';

@Component({
  selector: 'jhi-constructor-visor-container',
  templateUrl: './constructor-visor-container.component.html',
  styleUrls: ['./constructor-visor-container.component.scss']
})
export class ConstructorVisorContainerComponent implements OnInit {
  contentBlocks = [
    {
      contentBlockType: 'titulo'
    },
    {
      contentBlockType: 'texto'
    },
    {
      contentBlockType: 'texto'
    },
    {
      contentBlockType: 'imagen'
    },
    {
      contentBlockType: 'imagen_texto'
    }
  ];

  constructor(private contentBlocksService: ContentBlocksService) {}

  ngOnInit(): void {
    /*
    this.contentBlocks = this.contentBlocksService.getContentBlocks();
    */
  }
}
