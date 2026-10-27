import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import {
  HttpTestingController,
  provideHttpClientTesting,
} from '@angular/common/http/testing';
import { provideRouter } from '@angular/router';
import { AuthService } from './auth';

describe('AuthService', () => {
  let service: AuthService;
  let httpTesting: HttpTestingController;

  beforeEach(() => {
    localStorage.clear();
    TestBed.configureTestingModule({
      providers: [provideRouter([{ path: 'login', children: [] }]), provideHttpClient(), provideHttpClientTesting()],
    });
    service = TestBed.inject(AuthService);
    httpTesting = TestBed.inject(HttpTestingController);
  });

  afterEach(() => httpTesting.verify());

  it('updates the photo and the stored user', () => {
    service.updatePhoto('https://x.test/a.png').subscribe();

    const req = httpTesting.expectOne('/auth-example/api/users/me/photo');
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual({ photoUrl: 'https://x.test/a.png' });
    req.flush({ username: 'john', photoUrl: 'https://x.test/a.png' });

    expect(service.currentUser()?.photoUrl).toBe('https://x.test/a.png');
    expect(localStorage.getItem('current_user')).toContain('a.png');
  });

  it('logs in, stores the token and loads the user from /users/me', () => {
    service.login({ username: 'john', password: 'secret1' }).subscribe();

    const login = httpTesting.expectOne('/auth-example/api/users/login');
    expect(login.request.method).toBe('POST');
    login.flush({ token: 'jwt', type: 'Bearer' });

    httpTesting
      .expectOne('/auth-example/api/users/me')
      .flush({ id: 1, username: 'john', email: 'j@e.com', age: 30, role: 'USER' });

    expect(service.isAuthenticated()).toBe(true);
    expect(service.getToken()).toBe('jwt');
    expect(service.currentUser()?.email).toBe('j@e.com');
    expect(service.currentUser()?.age).toBe(30);
  });

  it('falls back to the token claims when /users/me fails', () => {
    const token = `h.${btoa(JSON.stringify({ sub: 'john', role: 'USER' }))}.s`;
    service.login({ username: 'john', password: 'secret1' }).subscribe();
    httpTesting.expectOne('/auth-example/api/users/login').flush({ token, type: 'Bearer' });
    httpTesting
      .expectOne('/auth-example/api/users/me')
      .flush('err', { status: 500, statusText: 'Server Error' });

    expect(service.currentUser()).toEqual({ username: 'john', role: 'USER' });
  });

  it('does not store a token when login fails', () => {
    service.login({ username: 'john', password: 'bad' }).subscribe({ error: () => undefined });
    httpTesting
      .expectOne('/auth-example/api/users/login')
      .flush('no', { status: 401, statusText: 'Unauthorized' });

    expect(service.isAuthenticated()).toBe(false);
  });

  it('posts registration and recovery requests', () => {
    service
      .register({ username: 'a', email: 'a@b.c', password: 'secret1', age: 20, role: 'USER' })
      .subscribe();
    expect(httpTesting.expectOne('/auth-example/api/users').request.method).toBe('POST');

    service.recoverPassword({ email: 'a@b.c' }).subscribe();
    const recover = httpTesting.expectOne('/auth-example/api/users/recover-password');
    expect(recover.request.body).toEqual({ email: 'a@b.c' });
  });

  it('clears the session on logout', () => {
    localStorage.setItem('access_token', 'jwt');
    service.logout();

    expect(service.isAuthenticated()).toBe(false);
  });
});
