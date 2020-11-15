export type UserState = {
    loggedIn: boolean;
    currentUser?: User;
}

export type User = {
    user_id: number;
    username: string;
    first_name: string;
    last_name: string;
    email: string;
}