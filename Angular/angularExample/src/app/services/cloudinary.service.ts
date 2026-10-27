import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface UploadResponse {
  public_id: string;
  secure_url: string;
}

export interface CloudinaryConfig {
  cloudName: string;
  uploadPreset: string;
}

@Injectable({
  providedIn: 'root',
})
export class CloudinaryService {
  private cloudName = environment.cloudinary.cloudName;
  private uploadPreset = environment.cloudinary.uploadPreset;
  private apiUrl = `https://api.cloudinary.com/v1_1/${this.cloudName}/upload`;

  constructor(private http: HttpClient) {}

  uploadImage(file: File): Observable<UploadResponse> {
    if (!this.cloudName || !this.uploadPreset) {
      throw new Error('Cloudinary not configured. Set cloudName and uploadPreset in src/environments/environment.ts.');
    }

    const formData = new FormData();
    formData.append('file', file);
    formData.append('upload_preset', this.uploadPreset);

    return this.http.post<UploadResponse>(this.apiUrl, formData);
  }
}
