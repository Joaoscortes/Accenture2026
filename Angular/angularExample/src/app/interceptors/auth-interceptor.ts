import { DOCUMENT } from '@angular/common';
import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth';

export const authInterceptor: HttpInterceptorFn = (request, next) => {
  const token = inject(AuthService).getToken();
  const document = inject(DOCUMENT);

  if (!token) {
    return next(request);
  }

  const appOrigin = new URL(document.baseURI).origin;
  const requestOrigin = new URL(request.url, document.baseURI).origin;
  if (requestOrigin !== appOrigin) {
    return next(request);
  }

  return next(
    request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    }),
  );
};
