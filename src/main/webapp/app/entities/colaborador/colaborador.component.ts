import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IColaborador } from 'app/shared/model/colaborador.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ColaboradorService } from './colaborador.service';
import { ColaboradorDeleteDialogComponent } from './colaborador-delete-dialog.component';

@Component({
  selector: 'jhi-colaborador',
  templateUrl: './colaborador.component.html'
})
export class ColaboradorComponent implements OnInit, OnDestroy {
  colaboradors?: IColaborador[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected colaboradorService: ColaboradorService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    this.colaboradorService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IColaborador[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInColaboradors();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IColaborador): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInColaboradors(): void {
    this.eventSubscriber = this.eventManager.subscribe('colaboradorListModification', () => this.loadPage());
  }

  delete(colaborador: IColaborador): void {
    const modalRef = this.modalService.open(ColaboradorDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.colaborador = colaborador;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IColaborador[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/colaborador'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.colaboradors = data ? data : [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
