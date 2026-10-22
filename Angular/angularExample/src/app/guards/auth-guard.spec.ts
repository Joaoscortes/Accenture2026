import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import {
  ActivatedRouteSnapshot,
  Router,
  RouterStateSnapshot,
  UrlTree,
  provideRouter,
} from '@angular/router';
import { authGuard } from './auth-guard';

describe('authGuard', () => {
  const route = {} as ActivatedRouteSnapshot;
  const state = { url: '/profile' } as RouterStateSnapshot;
  const executeGuard = () => TestBed.runInInjectionContext(() => authGuard(route, state));

  beforeEach(() => {
    localStorage.clear();
    TestBed.configureTestingModule({
      providers: [provideRouter([]), provideHttpClient()],
    });
  });

  it('redirects unauthenticated users to login with their requested URL', () => {
    const result = executeGuard() as UrlTree;

    expect(TestBed.inject(Router).serializeUrl(result)).toBe('/login?returnUrl=%2Fprofile');
  });

  it('allows authenticated users to continue', () => {
    localStorage.setItem('access_token', 'jwt');

    expect(executeGuard()).toBe(true);
  });
});
