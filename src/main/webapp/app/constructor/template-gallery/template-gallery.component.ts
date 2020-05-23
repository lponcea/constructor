import { Component, OnInit } from '@angular/core';
import { NavigationControlsService } from '../../services/navigation-controls.service';
import { ContentBlocksService } from 'app/services/content-blocks.service';
import { TipoComponenteService } from 'app/entities/tipo-bloque-componente/tipo-componente.service';
import { ITipoBloqueComponentes } from 'app/shared/model/tipo-bloque-componentes.model';
import { map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-template-gallery',
  templateUrl: './template-gallery.component.html',
  styleUrls: ['./template-gallery.component.scss']
})
export class TemplateGalleryComponent implements OnInit {
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
  templates: ITipoBloqueComponentes[] = [];
  filteredTemplates: ITipoBloqueComponentes[];

  constructor(
    private contentBlocksService: ContentBlocksService,
    private navigationControlsService: NavigationControlsService,
    private tipoComponenteService: TipoComponenteService
  ) {
    /*
    this.templates = [
      {
        id: 1,
        nombre: 'titulo',
        iconPath: '../../../content/images/ab1.png',
        tags: 'text',
        tiposComponentes: [
          {
            id: 1,
            nombre: 'text'
          }
        ]
      },
      {
        id: 2,
        nombre: 'texto',
        iconPath: '../../../content/images/ab2.png',
        tags: 'text',
        tiposComponentes: [
          {
            id: 1,
            nombre: 'text'
          }
        ]
      },
      {
        id: 3,
        nombre: 'imagen',
        iconPath: '../../../content/images/ab3.png',
        tags: 'image',
        tiposComponentes: [
          {
            id: 2,
            nombre: 'image'
          }
        ]
      },
      {
        id: 4,
        nombre: 'imagen_texto',
        iconPath: '../../../content/images/ab4.png',
        tags: 'image text',
        tiposComponentes: [
          {
            id: 1,
            nombre: 'text'
          }
        ]
      }
    ];
    */
    // Obtener las plantillas del llamdo de los tipos de bloques de contenido
    this.tipoComponenteService
      .query()
      .pipe(
        map((res: HttpResponse<ITipoBloqueComponentes[]>) => {
          return res.body ? res.body : [];
        })
      )
      .subscribe((resBody: ITipoBloqueComponentes[]) => {
        this.templates = resBody;
        console.error(this.templates);
        // Enviar plantillas a servicio para utilizar en filmStrip
        this.contentBlocksService.setTemplates(this.templates);
      });
    this.filteredTemplates = this.templates;
  }

  ngOnInit(): void {}

  selectTemplate(selectedTemplate: any): void {
    this.contentBlocksService.setSelectedBlock(selectedTemplate);
    this.navigationControlsService.setOpenTemplateGallery(false);
  }

  selectContentBlock(selectedContentBlockIndex: number): void {
    this.selectedContentBlockIndex = selectedContentBlockIndex;
    this.selectTemplate(this.templates[selectedContentBlockIndex]);
  }

  filterTemplates(filter: any): void {
    this.restoreSelectedFilters();
    filter.selected = true;
    this.filteredTemplates = [];
    for (let i = 0; i < this.templates.length; i++) {
      if (this.templates[i].tags!.includes(filter.filter)) {
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
