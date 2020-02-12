import { Component } from '@angular/core';
import { NavigationControlsService } from '../../services/navigation-controls.service';

@Component({
  selector: 'jhi-template-gallery',
  templateUrl: './template-gallery.component.html',
  styleUrls: ['./template-gallery.component.scss']
})
export class TemplateGalleryComponent {
  templates = [
    {
      type: 'tres_columnas_imagen_texto_filas'
    },
    {
      type: 'imagen_texto_columnas'
    }
  ];

  constructor(private navigationControlsService: NavigationControlsService) {}

  selectTemplate(selectedTemplateType: string): void {
    this.navigationControlsService.selectTemplate(selectedTemplateType);
    this.navigationControlsService.setOpenTemplateGallery(false);
  }
}
