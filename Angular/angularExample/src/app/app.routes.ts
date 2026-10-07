import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Main } from './pages/main/main';
import { Profile } from './pages/profile/profile';
import { NotFound } from './pages/not-found/not-found';
import { Layout } from './pages/layout/layout';
import { authGuard } from './guards/auth-guard';

export const routes: Routes = [
  {
    path: '',
    component: Layout,
    canActivate: [authGuard],
    children: [
      {
        path: '',
        component: Main,
      },
      {
        path: 'profile',
        component: Profile,
      }
    ],
  },
  {
    path: 'login',
    component: Login,
  },
  {
    path: 'not-found',
    component: NotFound,
  },
  {
    path: '**',
    redirectTo: 'not-found',
  },
];
