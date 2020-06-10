import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { ImageService } from 'app/services/image.service';
import { JhiEventManager, JhiEventWithContent } from 'ng-jhipster';
import { FileUploadService } from 'app/services/file-upload.service';
import { CurrentCourseService } from 'app/services/current-course.service';
import { SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'jhi-constructor-component-properties',
  templateUrl: './constructor-component-properties.component.html',
  styleUrls: ['./constructor-component-properties.component.scss']
})
export class ConstructorComponentPropertiesComponent implements OnInit, OnDestroy {
  subscription: Subscription;
  imgSrc: SafeUrl = '';
  defaultImageUrl = './../../../content/images/cover_upload.png';
  maxImageSize = 30000000;
  allowedFileTypes: any = ['image/jpg', 'image/png', 'image/jpeg'];
  selectedFiles = [];
  id = 0; // Id de curso o módulo a guardar.
  type = 'course'; // course, module
  @ViewChild('fileInput', { static: false }) fileInput: any;

  constructor(
    public imageService: ImageService,
    public eventManager: JhiEventManager,
    public fileUploadService: FileUploadService,
    public currentCourseService: CurrentCourseService
  ) {
    this.subscription = this.imageService.getImgSrc().subscribe(imgSrc => {
      this.imgSrc = imgSrc;
    });
    if (this.type === 'course') {
      this.subscription = this.currentCourseService.getCurrentCourse().subscribe(currentCourse => {
        if (currentCourse.id) {
          this.id = currentCourse.id;
        }
      });
    }
  }

  selectFile(event: any): void {
    if (event.target.files.length) {
      // Validar tamaño máximo
      if (event.target.files[0].size > this.maxImageSize) {
        this.eventManager.broadcast(
          new JhiEventWithContent('constructorApp.validationError', { message: 'constructorApp.curso.validations.fileSize' })
        );
        event.target.files = [];
        return;
        // Validar tipo de archivo
      } else if (!this.allowedFileTypes.includes(event.target.files[0].type)) {
        this.eventManager.broadcast(
          new JhiEventWithContent('constructorApp.validationError', { message: 'constructorApp.curso.validations.fileType' })
        );
        event.target.files = [];
        return;
      } else {
        this.selectedFiles = event.target.files;
        this.fileUploadService.pushFileStorage(this.selectedFiles[0], this.id).subscribe(data => {
          this.fileUploadService.getImage(data.path);
          this.imageService.setPathUrl(data.path);
        });
      }
    }
  }

  deleteImage(): void {
    this.imageService.setImgSrc('');
    this.imageService.setPathUrl('');
    this.fileInput.nativeElement.value = '';
  }

  ngOnInit(): void {}

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
