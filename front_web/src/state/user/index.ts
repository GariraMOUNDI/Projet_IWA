import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { User, UserState } from './types';
import { RootState } from '../store';
// import { cookies, COOKIE_NAMES } from '../../constants/cookies';

const initialState: UserState = {
    loggedIn: false,
    currentUser: undefined
    // username: cookies.get( COOKIE_NAMES.username ),
    // loggedIn: cookies.get( COOKIE_NAMES.loggedIn ),
    // isAdminOf: cookies.get( COOKIE_NAMES.admin ),
    // portal: cookies.get( COOKIE_NAMES.admin ) ? cookies.get( COOKIE_NAMES.admin )[ 0 ] : null
};

type LoginParams = {
    user: User;
};

/**
 * Create the actions, reducers, and state for a user.
 * https://redux-toolkit.js.org/api/createSlice
 */
export const userSlice = createSlice( {
    name: 'user',
    initialState,
    reducers: {
        // Pass the username into the login action
        // Change the state accordingly
        login: ( state, action: PayloadAction<LoginParams> ) => {
            state.loggedIn = true;
            state.currentUser = action.payload.user;
            // cookies.set( COOKIE_NAMES.loggedIn, true, { path: '/' } );
            // cookies.set( COOKIE_NAMES.username, action.payload.user, { path: '/' } );
            // cookies.set( COOKIE_NAMES.admin, action.payload.adminOf, { path: '/' } );
        },
        // Return to null state and remove cookies
        logout: ( state ) => {
            state.loggedIn = false;
            state.currentUser = undefined;
            // cookies.remove( COOKIE_NAMES.username );
            // cookies.remove( COOKIE_NAMES.loggedIn );
            // cookies.remove( COOKIE_NAMES.admin );
        }
    },
} );

// Export the actions to be used as functions throughout the app
export const { login, logout } = userSlice.actions;

// // Create custom selector functions (essentially a getter)
// export const isLoggedIn = ( state: RootState ) => state.user.loggedIn;

// export const getUsername = ( state: RootState ) => state.user.username;

// export const portal = ( state: RootState ) => state.user.portal;

// export const adminOf = ( state: RootState ) => state.user.isAdminOf;

// Grab the reducer and export it as the default
const userReducer = userSlice.reducer;

export default userReducer;