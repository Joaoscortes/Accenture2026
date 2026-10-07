import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  
  //TODO: Replace with actual authentication check
  const isAuthenticated = true; // Replace with actual authentication check

  if (!isAuthenticated) {
    router.navigate(['/login']); // Redirect to login page if not authenticated
    return false;
  }
  return true;
};
