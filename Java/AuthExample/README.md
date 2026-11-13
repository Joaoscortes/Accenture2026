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

### Addresses and user deletion

- After logging in, use `Authorization: Bearer <token>` with `GET /api/addresses`,
  `POST /api/addresses`, or `GET`, `PUT`, `DELETE /api/addresses/{id}`. Addresses
  belong to the authenticated user; another user's address returns 404.
- POST and PUT accept `{ "street": "1 Main St", "city": "Lisbon",
  "postalCode": "1000-001", "country": "Portugal" }`. All fields are required,
  non-blank, and at most 255 characters; PUT replaces all fields.
- `DELETE /api/users/{id}` sets `deleted` to true and clears username and email
  instead of removing the user or their addresses. A user can delete their own
  account; an administrator can delete any account. Deleted users are excluded
  from queries and login. Registration always creates a `USER`, not an admin.
- Tokens issued before this change must be replaced by logging in again. Tokens
  are bound to user IDs so they cannot be reused when a username is reassigned.
- For an existing MySQL database without these schema changes, back it up and
  apply `src/main/resources/sql/migrate-addresses-soft-delete.sql` once. If
  `deleted` or the address table already exists, adapt the migration first;
  ensure existing `deleted` values are non-null and default to false.
  The current `persistence.xml` specifies **drop-and-create**, which destroys
  data at deployment; disable that setting and the seed script before using
  an existing database or applying the migration.

## Security Notes

- Passwords are never stored in plain text
- JWT tokens have expiration times
- Authentication filter protects endpoints from unauthorized access