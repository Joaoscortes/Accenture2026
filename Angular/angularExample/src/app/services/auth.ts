import { HttpClient } from '@angular/common/http';
import { Injectable, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, map, of, switchMap, tap } from 'rxjs';
import {
  LoginRequest,
  LoginResponse,
  RecoverPasswordRequest,
} from '../model/login-request';
import { RegisterRequest, User } from '../model/user';

const API_URL = '/auth-example/api/users';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly router = inject(Router);
  private readonly tokenKey = 'access_token';
  private readonly userKey = 'current_user';

  private readonly currentUserSignal = signal<User | null>(this.loadUser());
  readonly currentUser = this.currentUserSignal.asReadonly();

  login(credentials: LoginRequest): Observable<User> {
    return this.http.post<LoginResponse>(`${API_URL}/login`, credentials).pipe(
      tap((response) => localStorage.setItem(this.tokenKey, response.token)),
      switchMap((response) =>
        this.http.get<User>(`${API_URL}/me`).pipe(
          catchError(() =>
            of(this.profileFromToken(response.token, credentials.username)),
          ),
        ),
      ),
      tap((user) => this.setUser(user)),
    );
  }

  register(request: RegisterRequest): Observable<void> {
    return this.http.post<unknown>(API_URL, request).pipe(map(() => undefined));
  }

  recoverPassword(request: RecoverPasswordRequest): Observable<void> {
    return this.http
      .post<unknown>(`${API_URL}/recover-password`, request)
      .pipe(map(() => undefined));
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.userKey);
    this.currentUserSignal.set(null);
    void this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  // Fallback when /users/me is unavailable: the JWT carries the username (sub) and role.
  private profileFromToken(token: string, username: string): User {
    try {
      const payload = token.split('.')[1].replace(/-/g, '+').replace(/_/g, '/');
      const claims = JSON.parse(atob(payload)) as { sub?: unknown; role?: unknown };
      return {
        username: typeof claims.sub === 'string' ? claims.sub : username,
        role: typeof claims.role === 'string' ? claims.role : undefined,
      };
    } catch {
      return { username };
    }
  }

  private setUser(user: User): void {
    localStorage.setItem(this.userKey, JSON.stringify(user));
    this.currentUserSignal.set(user);
  }

  private loadUser(): User | null {
    const stored = localStorage.getItem(this.userKey);
    if (stored === null) {
      return null;
    }
    try {
      const parsed = JSON.parse(stored) as { username?: unknown };
      return typeof parsed.username === 'string' ? (parsed as User) : null;
    } catch {
      return null;
    }
  }
}
