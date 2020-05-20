import { Component, Input, AfterViewInit, OnInit } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { TextService } from 'app/services/text.service';

@Component({
  selector: 'jhi-constructor-text',
  templateUrl: './constructor-text.component.html',
  styleUrls: ['./constructor-text.component.scss']
})
export class ConstructorTextComponent implements OnInit, AfterViewInit {
  // htmlContent: any;
  private _htmlContent = '';
  get htmlContent(): string {
    return this._htmlContent;
  }
  @Input()
  set htmlContent(val: string) {
    this._htmlContent = val;
    this.textService.setText(this.htmlContent);
  }
  title = false;

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
    defaultParagraphSeparator: 'p',
    defaultFontName: '',
    defaultFontSize: '4',
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

  constructor(private textService: TextService) {
    this.textService.getText().subscribe(text => {
      if (text) {
        this._htmlContent = text;
      }
    });
  }

  ngOnInit(): void {
    if (this.title) {
      this.editorConfig.toolbarHiddenButtons![0].push('insertUnorderedList');
      this.editorConfig.toolbarHiddenButtons![0].push('insertOrderedList');
      this.editorConfig.toolbarHiddenButtons![0].push('heading');
      this.editorConfig.toolbarHiddenButtons![1].push('fontSize');
    }
  }

  ngAfterViewInit(): void {}

  fixTitleOptions(): void {
    /*
    const options = document.getElementsByClassName("ae-picker-item");
    for(let i = 0; i < options.length; i++) {
      console.error(options[i]);
      try {
        if(i < 6) {
          options[i].firstChild!.nodeValue = "Título " + (i + 1);
        }
        else {
          options[i].
        }
      }
      catch(e) {

       }
    }
    */
    /*
    document.getElementsByClassName("ae-picker-item")[0].hidden = true
    document.getElementsByClassName("ae-picker-item")[0].firstChild.nodeValue = "Título 1"
    */
  }

  restoreTitle(text: string): string {
    if (!text.startsWith('<h1>')) {
      return '<h1>' + text + '</h1>';
    }
    return text;
  }
}
