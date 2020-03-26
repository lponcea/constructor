import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IModalidad } from 'app/shared/model/modalidad.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ModalidadService } from './modalidad.service';
import { ModalidadDeleteDialogComponent } from './modalidad-delete-dialog.component';

@Component({
  selector: 'jhi-modalidad',
  templateUrl: './modalidad.component.html'
})
export class ModalidadComponent implements OnInit, OnDestroy {
  modalidads?: IModalidad[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected modalidadService: ModalidadService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    this.modalidadService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IModalidad[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInModalidads();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IModalidad): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInModalidads(): void {
    this.eventSubscriber = this.eventManager.subscribe('modalidadListModification', () => this.loadPage());
  }

  delete(modalidad: IModalidad): void {
    const modalRef = this.modalService.open(ModalidadDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.modalidad = modalidad;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IModalidad[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/modalidad'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.modalidads = data ? data : [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
