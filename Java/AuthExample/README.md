# Auth Example Application

This is a Java-based authentication example application with JWT token support and secure password handling.

## Features Implemented

1. **Password Encryption**: Passwords are now securely hashed using BCrypt before storage
2. **JWT Authentication**: Token-based authentication system for securing endpoints
3. **Login Endpoint**: Authentication endpoint that issues JWT tokens upon successful login

## Dependencies Added

- **BCrypt**: For secure password hashing (org.mindrot:jbcrypt:0.4)
- **JJWT**: For JWT token generation and validation (io.jsonwebtoken:jjwt-api, jjwt-impl, jjwt-jackson)

## Implementation Details

### Password Encryption
- All passwords are automatically hashed using BCrypt before being stored in the database
- The `User` model now uses `PasswordUtil.hashPassword()` when setting passwords
- Password verification is performed using `PasswordUtil.verifyPassword()`

### JWT Implementation
- JWT tokens are generated with 24-hour expiration
- A filter (`AuthenticationFilter`) validates tokens on protected endpoints
- Login endpoint at `/users/login` accepts username/password and returns a JWT token

### Usage

1. **Create User**: POST to `/users` with user data (password will be automatically hashed)
2. **Login**: POST to `/users/login` with `{ "username": "user", "password": "pass" }`
3. **Access Protected Resources**: Include Authorization header with Bearer token for subsequent requests

## Security Notes

- Passwords are never stored in plain text
- JWT tokens have expiration times
- Authentication filter protects endpoints from unauthorized access