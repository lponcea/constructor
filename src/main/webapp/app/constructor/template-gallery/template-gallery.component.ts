import { Component } from '@angular/core';
import { NavigationControlsService } from '../../services/navigation-controls.service';

@Component({
  selector: 'jhi-template-gallery',
  templateUrl: './template-gallery.component.html',
  styleUrls: ['./template-gallery.component.scss']
})
export class TemplateGalleryComponent {
  selectedContentBlockIndex = -1;
  filters = [
    {
      text: 'Texto',
      filter: 'text',
      selected: false
    },
    {
      text: 'Imagen',
      filter: 'image',
      selected: false
    }
  ];
  templates = [
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
  filteredTemplates = this.templates;

  constructor(private navigationControlsService: NavigationControlsService) {}

  selectTemplate(selectedTemplateType: string): void {
    this.navigationControlsService.selectTemplate(selectedTemplateType);
    this.navigationControlsService.setOpenTemplateGallery(false);
  }

  /*
   * Obtiene la imagen para el fimrstrip de acuerdo con el tipo de bloque de contenido.
   */
  getContentBlockImage(contentBlockType: string): string {
    let path = '';
    for (const imagePath of this.templates) {
      if (imagePath.contentBlockType === contentBlockType) {
        path = imagePath['path'];
      }
    }
    return path;
  }

  selectContentBlock(selectedContentBlockIndex: number): void {
    this.selectedContentBlockIndex = selectedContentBlockIndex;
  }

  filterTemplates(filter: any): void {
    this.restoreSelectedFilters();
    filter.selected = true;
    this.filteredTemplates = [];
    for (let i = 0; i < this.templates.length; i++) {
      if (this.templates[i].tags.includes(filter.filter)) {
        this.filteredTemplates.push(this.templates[i]);
      }
    }
  }

  restoreSelectedFilters(): void {
    for (let i = 0; i < this.filters.length; i++) {
      this.filters[i].selected = false;
    }
  }
}
