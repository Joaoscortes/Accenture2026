import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  imports: [ReactiveFormsModule, RouterLink],
  selector: 'app-recover-password',
  styleUrl: './recover-password.scss',
  templateUrl: './recover-password.html',
})
export class RecoverPassword {
  private readonly formBuilder = inject(FormBuilder);
  private readonly authService = inject(AuthService);

  readonly form = this.formBuilder.nonNullable.group({
    email: ['', [Validators.required, Validators.email]],
  });

  sent = false;
  error: string | null = null;
  isSubmitting = false;

  submit(): void {
    this.error = null;
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.isSubmitting = true;
    this.authService.recoverPassword(this.form.getRawValue()).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.sent = true;
      },
      error: () => {
        this.isSubmitting = false;
        this.error = 'Unable to process the request. Please try again later.';
      },
    });
  }
}
