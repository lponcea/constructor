import { Component, OnInit, Input } from '@angular/core';
import { BloqueComponentes } from 'app/shared/model/bloque-componentes.model';

@Component({
  selector: 'jhi-content-block3',
  templateUrl: './content-block3.component.html',
  styleUrls: ['./content-block3.component.scss']
})
export class ContentBlock3Component implements OnInit {
  imgSrc = './../../../../content/images/cover_upload.png';
  @Input() contentBlock?: BloqueComponentes;

  constructor() {}

  ngOnInit(): void {}
}
