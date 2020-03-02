import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { FileUploadService } from './../../../services/file-upload.service';
import { Subscription } from 'rxjs';
import { NavigationControlsService } from 'app/services/navigation-controls.service';

@Component({
  selector: 'jhi-content-block3',
  templateUrl: './content-block3.component.html',
  styleUrls: ['./content-block3.component.scss']
})
export class ContentBlock3Component implements OnInit {
  videoUrl: SafeUrl = '';
  subscription: Subscription;

  constructor(
    private fileUploadService: FileUploadService,
    private sanitizer: DomSanitizer,
    private navigationControlsService: NavigationControlsService
  ) {
    this.subscription = this.navigationControlsService.getVideoPath().subscribe(videoPath => {
      if (videoPath) {
        this.getVideo(videoPath.videoPath);
      }
    });
  }

  ngOnInit(): void {}

  getVideo(videoPath: string): void {
    this.fileUploadService.getFile(videoPath).subscribe(data => {
      const objectUrl = URL.createObjectURL(data.body);
      this.videoUrl = this.sanitizer.bypassSecurityTrustUrl(objectUrl);
    });
  }
}
