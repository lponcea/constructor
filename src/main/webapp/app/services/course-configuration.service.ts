import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CourseConfigurationService {
  private selectedTab = new Subject<any>();
  private errorTabIndex = new Subject<any>();
  private clearTabErrorsMessage = new Subject<any>();

  constructor() {}

  setSelectedTab(index: number): void {
    this.selectedTab.next({
      selectedTab: index
    });
  }

  getSelectedTab(): Observable<any> {
    return this.selectedTab.asObservable();
  }

  setErrorTabIndex(index: number): void {
    this.errorTabIndex.next({
      errorTabIndex: index
    });
  }

  getErrorTabIndex(): Observable<any> {
    return this.errorTabIndex.asObservable();
  }

  clearTabErrors(): void {
    this.clearTabErrorsMessage.next({
      clearTabErrorsMessage: true
    });
  }

  getClearTabsMessage(): Observable<any> {
    return this.clearTabErrorsMessage.asObservable();
  }
}
