import { Component, OnInit } from '@angular/core';
import { NavigationControlsService } from '../../services/navigation-controls.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'jhi-right-sidebar',
  templateUrl: './right-sidebar.component.html',
  styleUrls: ['./right-sidebar.component.scss']
})
export class RightSidebarComponent implements OnInit {
  openTemplateGallery = false;
  subscription: Subscription;

  constructor(private navigationControlsService: NavigationControlsService) {
    this.subscription = this.navigationControlsService.getTemplateGalleryStatus().subscribe(openTemplateGallery => {
      /*
      alert(openTemplateGallery);
      alert(openTemplateGallery.openTemplateGallery);
      */
      if (openTemplateGallery) {
        this.openTemplateGallery = openTemplateGallery.openTemplateGallery;
      }
    });
  }

  ngOnInit(): void {}
}
