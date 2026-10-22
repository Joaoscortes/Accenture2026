import { Component, inject } from '@angular/core';
import { AuthService } from '../../services/auth';

@Component({
  imports: [],
  selector: 'app-profile',
  styleUrl: './profile.scss',
  templateUrl: './profile.html',
})
export class Profile {
  readonly user = inject(AuthService).currentUser;
}
