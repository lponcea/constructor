import { Component } from '@angular/core';
import { LoginModalService } from 'app/core/login/login-modal.service';
import { LoginService } from 'app/core/login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'jhi-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.scss']
})
export class TopBarComponent {
  openMenu = false;
  isNavbarCollapsed = true;

  constructor(private loginService: LoginService, private loginModalService: LoginModalService, private router: Router) {}

  collapseNavbar(): void {
    this.isNavbarCollapsed = true;
  }

  login(): void {
    this.loginModalService.open();
  }

  logout(): void {
    this.router.navigate(['/']);
    this.collapseNavbar();
    this.loginService.logout();
  }
}
