import { Component, OnInit, OnDestroy } from '@angular/core';
import { NavigationControlsService } from '../../services/navigation-controls.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'jhi-right-sidebar',
  templateUrl: './right-sidebar.component.html',
  styleUrls: ['./right-sidebar.component.scss']
})
export class RightSidebarComponent implements OnInit, OnDestroy {
  openTemplateGallery = false;
  openProperties = false;
  subscription: Subscription;

  constructor(private navigationControlsService: NavigationControlsService) {
    this.subscription = this.navigationControlsService.getTemplateGalleryStatus().subscribe(openTemplateGallery => {
      if (openTemplateGallery) {
        this.openTemplateGallery = openTemplateGallery.openTemplateGallery;
      }
    });
    this.subscription = this.navigationControlsService.getOpenProperties().subscribe(openProperties => {
      this.openProperties = openProperties;
    });
  }

  ngOnInit(): void {}

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
