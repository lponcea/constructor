import { Component, OnDestroy, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ImageService } from 'app/services/image.service';
import { SafeUrl } from '@angular/platform-browser';
import { NavigationControlsService } from 'app/services/navigation-controls.service';
import { Componente } from 'app/shared/model/componente.model';

@Component({
  selector: 'jhi-constructor-image',
  templateUrl: './constructor-image.component.html',
  styleUrls: ['./constructor-image.component.scss']
})
export class ConstructorImageComponent implements OnInit, OnDestroy {
  defaultImageUrl: SafeUrl = './../../../../content/images/cover_upload.png';
  imgSrc: SafeUrl = '';
  editing = false;
  subscription: Subscription;
  @Input() component?: Componente;
  @Output() updateComponent = new EventEmitter();

  constructor(public imageService: ImageService, public navigationControlsService: NavigationControlsService) {
    this.subscription = this.imageService.getEditing().subscribe(editing => (this.editing = editing));
    this.subscription = this.imageService.getImgSrc().subscribe(imgSrc => {
      if (this.editing) {
        this.imgSrc = imgSrc;
      }
    });
  }

  selectImage(): void {
    this.imageService.setEditing(false);
    this.imageService.setImgSrc(this.imgSrc);
    this.editing = true;
    this.navigationControlsService.setOpenProperties(true);
  }

  ngOnInit(): void {
    if (this.component && this.component.contenido !== '') {
      this.component.contenido!;
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
