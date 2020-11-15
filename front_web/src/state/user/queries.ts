import { createAsyncThunk } from "@reduxjs/toolkit";
import { User } from "./types";

export const login = createAsyncThunk<User, { username: string, password: string; }>( 'user/login',
    async ( { username, password } ) => {
        // Call to API
        const response: User = {
            userId: 1234,
            username: "remirez",
            firstName: "Remy",
            lastName: "McConnell",
            email: "remirezmcc@gmail.com"
        };
        return response;
    }
);