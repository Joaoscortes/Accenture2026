import { Service } from '@angular/core';
import { User } from '../model/user';

@Service()
export class UserService {
    currentUser: User | null = null;

    public login(user: User) {
        //TODO: Implement authentication logic here before setting the current user
        user = new User({id: 1})
        
        this.currentUser = user;
    }
}
