import { Component, Input } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';

import { MessageServiceService } from './../../../services/message-service.service';

@Component({
  selector: 'jhi-constructor-text',
  templateUrl: './constructor-text.component.html',
  styleUrls: ['./constructor-text.component.scss']
})
export class ConstructorTextComponent {
  htmlContent: any;

  @Input() config: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    height: 'auto',
    minHeight: '0',
    maxHeight: 'auto',
    width: 'auto',
    minWidth: '0',
    translate: 'yes',
    enableToolbar: true,
    showToolbar: true,
    placeholder: 'Ingresa tu título aquí...',
    defaultParagraphSeparator: '',
    defaultFontName: 'Comic Sans MS',
    defaultFontSize: '',
    fonts: [{ class: 'comic-sans-ms', name: 'Comic Sans MS' }],
    customClasses: [
      {
        name: 'quote',
        class: 'quote'
      },
      {
        name: 'redText',
        class: 'redText'
      },
      {
        name: 'titleText',
        class: 'titleText',
        tag: 'h1'
      }
    ],
    uploadUrl: 'v1/image',
    sanitize: true,
    toolbarPosition: 'top',
    toolbarHiddenButtons: [
      ['strikeThrough', 'subscript', 'superscript'],
      ['justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull'],
      ['indent', 'outdent'],
      ['insertUnorderedList', 'insertOrderedList'],
      ['link', 'unlink', 'insertImage', 'insertVideo', 'insertHorizontalRule']
    ]
  };

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
