export interface IUser {}

export class User implements IUser {
    'id'?: number;
    'username'?: string;
    'password'?: string;

    constructor(data?: Partial<User>) {
        Object.assign(this, data);
    }
}