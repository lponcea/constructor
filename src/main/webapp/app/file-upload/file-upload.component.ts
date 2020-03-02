import { Component } from '@angular/core';
import { FileUploadService } from './../services/file-upload.service';
import { NavigationControlsService } from 'app/services/navigation-controls.service';

@Component({
  selector: 'jhi-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent {
  changeImage = false;
  selectedFiles = FileList;
  currentFileUpload: File = this.selectedFiles[0];

  constructor(private fileUploadService: FileUploadService, private navigationControlsService: NavigationControlsService) {}

  change($event: any): void {
    this.changeImage = true;
  }

  upload(): void {
    this.currentFileUpload = this.selectedFiles[0];
    this.fileUploadService.pushFileStorage(this.currentFileUpload).subscribe(event => {
      this.navigationControlsService.setVideoPath(event.path);
    });
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }
}
