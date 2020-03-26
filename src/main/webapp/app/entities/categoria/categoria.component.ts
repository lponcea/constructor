import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICategoria } from 'app/shared/model/categoria.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CategoriaService } from './categoria.service';
import { CategoriaDeleteDialogComponent } from './categoria-delete-dialog.component';

@Component({
  selector: 'jhi-categoria',
  templateUrl: './categoria.component.html'
})
export class CategoriaComponent implements OnInit, OnDestroy {
  categorias?: ICategoria[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected categoriaService: CategoriaService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    this.categoriaService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ICategoria[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInCategorias();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICategoria): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCategorias(): void {
    this.eventSubscriber = this.eventManager.subscribe('categoriaListModification', () => this.loadPage());
  }

  delete(categoria: ICategoria): void {
    const modalRef = this.modalService.open(CategoriaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.categoria = categoria;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ICategoria[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/categoria'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.categorias = data ? data : [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
