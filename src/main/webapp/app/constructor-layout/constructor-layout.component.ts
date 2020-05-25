import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { TextEditorBehaviorService } from 'app/services/text-editor-behavior.service';
import { ActivatedRoute } from '@angular/router';
import { CursoService } from 'app/entities/curso/curso.service';

@Component({
  selector: 'jhi-constructor-layout',
  templateUrl: './constructor-layout.component.html',
  styleUrls: ['./constructor-layout.component.scss']
})
export class ConstructorLayoutComponent implements OnInit {
  rightIsContracted = false;
  leftIsContracted = false;
  showTextEditor = false;
  subscription: Subscription;
  curso: any;

  constructor(
    private textEditorBehaviosService: TextEditorBehaviorService,
    private route: ActivatedRoute,
    private cursoService: CursoService
  ) {
    const cursoId = this.route.snapshot.paramMap.get('cursoId') as any;
    this.cursoService.find(cursoId).subscribe(response => {
      this.curso = response.body;
    });
    this.subscription = this.textEditorBehaviosService.getShowTextEditor().subscribe(showTextEditor => {
      this.showTextEditor = showTextEditor;
    });
  }

  ngOnInit(): void {}
}
