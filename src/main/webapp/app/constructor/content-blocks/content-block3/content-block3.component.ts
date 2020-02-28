import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { FileUploadService } from './../../../services/file-upload.service';

@Component({
  selector: 'jhi-content-block3',
  templateUrl: './content-block3.component.html',
  styleUrls: ['./content-block3.component.scss']
})
export class ContentBlock3Component implements OnInit {
  imageUrl: SafeUrl = '';

  constructor(private fileUploadService: FileUploadService, private sanitizer: DomSanitizer) {}

  ngOnInit(): void {}

  getVideo(): void {
    this.fileUploadService.getFile().subscribe(data => {
      const objectUrl = URL.createObjectURL(data.body);
      this.imageUrl = this.sanitizer.bypassSecurityTrustUrl(objectUrl);
    });
  }
}
