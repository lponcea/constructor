import { Component, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { MessageServiceService } from './../../../services/message-service.service';

@Component({
  selector: 'jhi-visor-text',
  templateUrl: './visor-text.component.html',
  styleUrls: ['./visor-text.component.scss']
})
export class VisorTextComponent implements OnDestroy {
  htmlContent = '';
  subscription: Subscription;
  imgSrc = './../../../../content/images/img3.png';

  constructor(private messageService: MessageServiceService) {
    this.subscription = this.messageService.getMessage().subscribe(message => {
      if (message) {
        this.htmlContent = message.text;
      } else {
        this.htmlContent = '';
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
