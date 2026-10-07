import { Component } from '@angular/core';
import { UserService } from '../../services/user-service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  imports: [ReactiveFormsModule],
  selector: 'app-login',
  styleUrl: './login.scss',
  templateUrl: './login.html',
})
export class Login {
  constructor(private userService: UserService) {}

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  onSubmit() {
    const { username, password } = this.loginForm.value;

    if (!!username && !!password) {
      this.userService.login({
        username: username ?? undefined,
        password: password ?? undefined,
      });
    }
  }
}
