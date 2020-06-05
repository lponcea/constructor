import { JhiAlertType } from 'ng-jhipster/service';

export class AlertError {
  constructor(public message: string, public key?: string, public params?: any, public type?: JhiAlertType) {}
}
