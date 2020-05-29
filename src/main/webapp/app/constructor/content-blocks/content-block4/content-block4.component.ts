import { Component, Input, Output, EventEmitter } from '@angular/core';
import { BloqueComponentes } from 'app/shared/model/bloque-componentes.model';
import { IComponente } from 'app/shared/model/componente.model';

@Component({
  selector: 'jhi-content-block4',
  templateUrl: './content-block4.component.html',
  styleUrls: ['./content-block4.component.scss']
})
export class ContentBlock4Component {
  imgSrc = './../../../../content/images/cover_upload.png';
  @Input() contentBlock?: BloqueComponentes;
  @Output() updateBlock = new EventEmitter();

  constructor() {}

  onUpdateComponent($event: Event, index: number): void {
    this.updateBlock.emit({
      newValue: $event['newValue'],
      type: $event['type'],
      componentIndex: index
    });
  }

  orderTextImage(components: IComponente[]): IComponente[] {
    let temp: IComponente[] = [];
    if (components.length > 1) {
      if (components[0].tipoComponente!.nombre === 'image') {
        temp.push(components[1]);
        temp.push(components[0]);
      } else {
        temp = components;
      }
    }
    return temp;
  }
}
