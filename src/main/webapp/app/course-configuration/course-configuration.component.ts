import { Component, Output, ViewChild } from '@angular/core';
import { CourseConfigurationService } from 'app/services/course-configuration.service';
import { CursoUpdateComponent } from 'app/entities/curso/curso-update.component';

@Component({
  selector: 'jhi-course-configuration',
  templateUrl: './course-configuration.component.html',
  styleUrls: ['./course-configuration.component.scss']
})
export class CourseConfigurationComponent {
  @Output() selectedTabIndex = 0;
  tabs = [
    {
      title: 'General',
      iconClass: 'c-icon-1'
    },
    {
      title: 'Avanzado',
      iconClass: 'c-icon-2'
    },
    {
      title: 'Recursos',
      iconClass: 'c-icon-3'
    }
  ];
  markElementStyles = {
    transform: 'translateX(' + this.selectedTabIndex * 80 + 'px)'
  };
  @ViewChild(CursoUpdateComponent, { static: false }) cursoUpdateComponent!: CursoUpdateComponent;

  constructor(private courseConfigurationService: CourseConfigurationService) {}

  changeSelectedIndex(index: number): void {
    this.selectedTabIndex = index;
    this.courseConfigurationService.setSelectedTab(index);
  }

  saveCourse(): void {
    this.cursoUpdateComponent.save();
  }
}
