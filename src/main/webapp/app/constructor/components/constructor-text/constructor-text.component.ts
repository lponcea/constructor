import { Component } from '@angular/core';

import { MessageServiceService } from './../../../services/message-service.service';
import { AngularEditorConfig } from '@kolkov/angular-editor';

@Component({
  selector: 'jhi-constructor-text',
  templateUrl: './constructor-text.component.html',
  styleUrls: ['./constructor-text.component.scss']
})
export class ConstructorTextComponent {
  htmlContent: any;
  editorConfig: AngularEditorConfig = {
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
    placeholder: 'Escribe aquí el texto',
    defaultParagraphSeparator: '<p>',
    defaultFontName: '',
    defaultFontSize: '',
    fonts: [
      { class: 'arial', name: 'Arial' },
      { class: 'times-new-roman', name: 'Times New Roman' },
      { class: 'calibri', name: 'Calibri' },
      { class: 'comic-sans-ms', name: 'Comic Sans MS' }
    ],
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
    uploadWithCredentials: false,
    sanitize: true,
    toolbarPosition: 'top',
    toolbarHiddenButtons: [
      ['strikeThrough', 'subscript', 'superscript', 'indent', 'outdent'],
      [
        'backgroundColor',
        'customClasses',
        'link',
        'unlink',
        'insertImage',
        'insertVideo',
        'insertHorizontalRule',
        'removeFormat',
        'toggleEditorMode'
      ]
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
