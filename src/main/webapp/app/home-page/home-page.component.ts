import { Component } from '@angular/core';

@Component({
  selector: 'jhi-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent {
  changeBoxLeft = 0;
  color1 = 255;
  color2 = 0;
  color3 = 0;
  opacity = 0;
  background = 'rgba(' + this.color1 + ', ' + this.color2 + ', ' + this.color3 + ', ' + this.opacity + ')';

  constructor() {}

  /*
  @HostListener('window:scroll', ['$event']) onScroll($event: Event): void {
    const porcentaje = (document.documentElement.scrollTop * 100) / document.documentElement.scrollHeight;
    this.changeBoxLeft = porcentaje;
    this.opacity = porcentaje / 100;
    this.color1 = 255 - (255 * porcentaje) / 100;
    this.color3 = (255 * porcentaje) / 100;
    this.background = 'rgba(' + this.color1 + ', ' + this.color2 + ', ' + this.color3 + ', ' + this.opacity + ')';
  }
  */
}
