import { Component } from '@angular/core';

import { MessageServiceService } from './../../../services/message-service.service';

@Component({
  selector: 'jhi-constructor-text',
  templateUrl: './constructor-text.component.html',
  styleUrls: ['./constructor-text.component.scss']
})
export class ConstructorTextComponent {
  htmlContent: any;

  constructor(private messageService: MessageServiceService) {}

  updateVisor(event: any): void {
    this.sendMessage(event);
  }

  sendMessage(text: string): void {
    this.messageService.sendMessage(text);
  }

  clearMessages(): void {
    this.messageService.clearMessages();
  }
}
