import { Component, ViewChild, OnInit } from '@angular/core';
import { IDRMLicenseServer } from 'videogular2/compiled/streaming';
import { VgDASH } from 'videogular2/compiled/src/streaming/vg-dash/vg-dash';
import { VgHLS } from 'videogular2/compiled/src/streaming/vg-hls/vg-hls';
import { VgAPI, BitrateOption } from 'videogular2/compiled/core';
import { Subscription, timer } from 'rxjs';
import { Observable } from 'rxjs';
import { FileUploadService } from './../../../services/file-upload.service';
// import { TimerObservable } from 'rxjs/Observable/TimerObservable';

export interface IMediaStream {
  type: 'vod' | 'dash' | 'hls';
  source: string;
  label: string;
  token?: string;
  licenseServers?: IDRMLicenseServer;
}

@Component({
  selector: 'jhi-content-block3',
  templateUrl: './content-block3.component.html',
  styleUrls: ['./content-block3.component.scss']
})
export class ContentBlock3Component implements OnInit {
  @ViewChild(VgDASH, { static: false })
  vgDash!: VgDASH;
  @ViewChild(VgHLS, { static: false })
  vgHls!: VgHLS;

  currentStream!: IMediaStream;
  api!: VgAPI;

  bitrates!: BitrateOption[];

  streams: IMediaStream[] = [
    {
      type: 'vod',
      label: 'VOD',
      source: 'http://static.videogular.com/assets/videos/videogular.mp4'
    },
    {
      type: 'dash',
      label: 'DASH: Multi rate Streaming',
      source: 'http://dash.edgesuite.net/akamai/bbb_30fps/bbb_30fps.mpd'
    },
    {
      type: 'dash',
      label: 'DASH: Live Streaming',
      source: 'https://24x7dash-i.akamaihd.net/dash/live/900080/dash-demo/dash.mpd'
    },
    {
      type: 'dash',
      label: 'DASH: DRM with Widevine',
      source: 'https://storage.googleapis.com/shaka-demo-assets/angel-one-widevine/dash.mpd',
      licenseServers: {
        'com.widevine.alpha': {
          serverURL: 'https://widevine-proxy.appspot.com/proxy'
        }
      }
    },
    {
      type: 'hls',
      label: 'HLS: Streaming',
      source: 'https://d2zihajmogu5jn.cloudfront.net/bipbop-advanced/bipbop_16x9_variant.m3u8'
    }
  ];

  constructor(private fileUploadService: FileUploadService) {}

  onPlayerReady(api: VgAPI): void {
    this.api = api;
  }

  ngOnInit(): void {
    this.currentStream = this.streams[0];
  }

  setBitrate(option: BitrateOption): void {
    switch (this.currentStream.type) {
      case 'dash':
        this.vgDash.setBitrate(option);
        break;

      case 'hls':
        this.vgHls.setBitrate(option);
        break;
    }
  }

  onClickStream(stream: IMediaStream): void {
    this.api.pause();
    this.bitrates = [];

    /*
    const timer = Observable.timer.create(0, 10);
    timer.subscribe(
        () => {
            this.currentStream = stream;
            timer.unsubscribe();
        }
    );
    */
  }

  getVideo(): void {
    this.fileUploadService.getFile().subscribe();
  }
}
