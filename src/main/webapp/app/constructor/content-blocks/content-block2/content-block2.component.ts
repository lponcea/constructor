import { Component, Input, Output, EventEmitter } from '@angular/core';
import { BloquesCurso } from 'app/shared/model/bloques-curso.model';

@Component({
  selector: 'jhi-content-block2',
  templateUrl: './content-block2.component.html',
  styleUrls: ['./content-block2.component.scss']
})
export class ContentBlock2Component {
  @Input() contentBlock?: BloquesCurso;
  @Output() updateBlock = new EventEmitter();

  constructor() {}

  // Actualizar valor de componente y del bloque de contenido en visorContainer
  onUpdateComponent($event: Event, index: number): void {
    this.updateBlock.emit({
      newValue: $event['newValue'],
      type: $event['type'],
      componentIndex: index
    });
  }
}
