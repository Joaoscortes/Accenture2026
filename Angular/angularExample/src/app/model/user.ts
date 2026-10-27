export interface User {
  username: string;
  email?: string;
  age?: number;
  role?: string;
  photoUrl?: string;
}

export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
  age: number;
  role: string;
}
