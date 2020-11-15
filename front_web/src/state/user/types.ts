export type UserState = {
    loggedIn: boolean;
    currentUser?: User;
    loading: boolean;
};

export type User = {
    userId: number;
    username: string;
    firstName: string;
    lastName: string;
    email: string;
};