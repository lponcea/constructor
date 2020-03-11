import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CourseConfigurationService {
  private selectedTab = new Subject<any>();

  constructor() {}

  setSelectedTab(index: number): void {
    this.selectedTab.next({
      selectedTab: index
    });
  }

  getSelectedTab(): Observable<any> {
    return this.selectedTab.asObservable();
  }
}
