import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { ICurso } from 'app/shared/model/curso.model';

@Injectable({
  providedIn: 'root'
})
export class CurrentCourseService {
  private currentCourse = new Subject<ICurso>();

  constructor() {}

  getCurrentCourse(): Observable<ICurso> {
    return this.currentCourse.asObservable();
  }

  setCurrentCourse(course: ICurso): void {
    this.currentCourse.next(course);
  }
}
