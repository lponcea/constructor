import { Component } from '@angular/core';

@Component({
  selector: 'jhi-constructor-layout',
  templateUrl: './constructor-layout.component.html',
  styleUrls: ['./constructor-layout.component.scss']
})
export class ConstructorLayoutComponent {
  rightIsContracted = false;
  leftIsContracted = false;
  showTextEditor = true;

  constructor() {}
}
