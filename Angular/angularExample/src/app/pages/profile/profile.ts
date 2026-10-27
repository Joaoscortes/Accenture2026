import { Component, inject, signal } from '@angular/core';
import { AuthService } from '../../services/auth';
import { CloudinaryService } from '../../services/cloudinary.service';

@Component({
  imports: [],
  selector: 'app-profile',
  styleUrl: './profile.scss',
  templateUrl: './profile.html',
})
export class Profile {
  private readonly auth = inject(AuthService);
  readonly user = this.auth.currentUser;
  private readonly cloudinaryService = inject(CloudinaryService);

  selectedFile: File | null = null;
  readonly uploading = signal(false);

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }

  uploadPhoto(): void {
    if (!this.selectedFile || !this.user()) {
      return;
    }

    this.uploading.set(true);

    this.cloudinaryService.uploadImage(this.selectedFile).subscribe({
      next: (response) => {
        this.auth.updatePhoto(response.secure_url).subscribe({
          next: () => {
            this.selectedFile = null;
            this.uploading.set(false);
          },
          error: (error) => {
            console.error('Saving photo failed:', error);
            this.uploading.set(false);
            alert('Photo upload failed!');
          },
        });
      },
      error: (error) => {
        console.error('Upload failed:', error);
        this.uploading.set(false);
        alert('Photo upload failed!');
      },
    });
  }
}
