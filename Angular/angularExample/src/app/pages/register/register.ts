import { HttpErrorResponse } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth';

// Role values are not documented by the API; adjust if the backend expects a different value.
const DEFAULT_ROLE = 'USER';

@Component({
  imports: [ReactiveFormsModule, RouterLink],
  selector: 'app-register',
  styleUrl: './register.scss',
  templateUrl: './register.html',
})
export class Register {
  private readonly formBuilder = inject(FormBuilder);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  readonly form = this.formBuilder.nonNullable.group({
    username: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    age: [18, [Validators.required, Validators.min(0), Validators.max(150)]],
    password: ['', [Validators.required, Validators.minLength(6)]],
  });

  error: string | null = null;
  isSubmitting = false;

  submit(): void {
    this.error = null;
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting = true;
    const value = this.form.getRawValue();
    this.authService
      .register({ ...value, age: Number(value.age), role: DEFAULT_ROLE })
      .subscribe({
        next: () =>
          void this.router.navigate(['/login'], { queryParams: { registered: 1 } }),
        error: (error: HttpErrorResponse) => {
          this.isSubmitting = false;
          this.error =
            error.status === 409
              ? 'This username or email is already registered.'
              : 'Unable to create the account. Please try again later.';
        },
      });
  }
}
