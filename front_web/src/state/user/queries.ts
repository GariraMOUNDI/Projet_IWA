import { createAsyncThunk } from "@reduxjs/toolkit";
import { Dispatch } from "redux";
import { logInUser, UserActionTypes } from ".";
import { User } from "./types";

// export const login = ( username: string, password: string ) => ( dispatch: Dispatch<UserActionTypes> ) => {
//     // Call to API
//     const response: User = {
//         userId: 1234,
//         username: "remirez",
//         firstName: "Remy",
//         lastName: "McConnell",
//         email: "remirezmcc@gmail.com"
//     };

//     dispatch( logInUser( response ) );
// };

// export const login = createAsyncThunk<User, { username: string, password: string; }>( 'user/logInUser',
//     async ( username: string, password: string ) => {
//         // Call to API
//         const response: User = {
//             userId: 1234,
//             username: "remirez",
//             firstName: "Remy",
//             lastName: "McConnell",
//             email: "remirezmcc@gmail.com"
//         };

//         return response;
//     }
// );