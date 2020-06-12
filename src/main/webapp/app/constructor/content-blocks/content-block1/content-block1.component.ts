import { Component, Input, Output, EventEmitter } from '@angular/core';
import { BloquesCurso } from 'app/shared/model/bloques-curso.model';

@Component({
  selector: 'jhi-content-block1',
  templateUrl: './content-block1.component.html',
  styleUrls: ['./content-block1.component.scss']
})
export class ContentBlock1Component {
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
