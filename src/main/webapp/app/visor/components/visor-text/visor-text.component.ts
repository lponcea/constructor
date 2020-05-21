import { Component, OnDestroy, Input, AfterViewInit, Output, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';
import { Componente } from 'app/shared/model/componente.model';
import { TextService } from 'app/services/text.service';

@Component({
  selector: 'jhi-visor-text',
  templateUrl: './visor-text.component.html',
  styleUrls: ['./visor-text.component.scss']
})
export class VisorTextComponent implements OnDestroy, AfterViewInit {
  htmlContent = '';
  exampleContent = '<h1>Título de ejemplo</h1><br><p>Este es un texto de ejemplo</p>';
  subscription: Subscription;
  imgSrc = './../../../../content/images/img3.png';
  editing = false;
  @Input() component?: Componente;
  @Input() templateType?: any;
  @Output() updateComponent = new EventEmitter();

  constructor(private textService: TextService) {
    this.subscription = this.textService.getEditing().subscribe(editing => {
      this.editing = editing;
    });
    this.subscription = this.textService.getText().subscribe(text => {
      if (text && this.editing) {
        this.htmlContent = text;
        this.component!.contenido = this.htmlContent;
        this.updateComponent.emit({ newValue: text, type: 'text' });
      }
    });
    /*
    this.subscription = this.messageService.getMessage().subscribe(message => {
      if (message) {
        this.htmlContent = message.text;
      } else {
        this.htmlContent = '';
      }
    });
    */
  }

  ngAfterViewInit(): void {}

  ngOnDestroy(): void {
    // this.subscription.unsubscribe();
  }

  editText(): void {
    this.textService.setEditing(false);
    this.editing = true;
    this.textService.setText(this.htmlContent);
    this.textService.setTemplateType(this.templateType);
  }
}
