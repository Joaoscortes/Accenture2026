import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Main } from './pages/main/main';
import { Profile } from './pages/profile/profile';
import { NotFound } from './pages/not-found/not-found';
import { Layout } from './pages/layout/layout';
import { authGuard } from './guards/auth-guard';
import { Info } from './pages/info/info';
import { Register } from './pages/register/register';
import { RecoverPassword } from './pages/recover-password/recover-password';

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
      },
      {
        path: 'info',
        component: Info,
      },
    ],
  },
  {
    path: 'login',
    component: Login,
  },
  {
    path: 'register',
    component: Register,
  },
  {
    path: 'recover-password',
    component: RecoverPassword,
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
