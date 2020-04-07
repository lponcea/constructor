import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthServerProvider } from 'app/core/auth/auth-jwt.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(public jwtHelper: JwtHelperService, public authServerProvider: AuthServerProvider) {}

  public isAuthenticated(): boolean {
    const token = this.authServerProvider.getToken();
    // Verificar si el token ha expirado
    if (token) {
      return !this.jwtHelper.isTokenExpired(token);
    } else {
      return false;
    }
  }
}
