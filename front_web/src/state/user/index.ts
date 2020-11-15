import { Action, ActionCreatorWithPayload, CaseReducerActions, createSlice, PayloadAction } from '@reduxjs/toolkit';
import { User, UserState } from './types';
import { RootState } from '../store';
import { Plan } from 'grommet-icons';
import { SliceActions } from '../../globals';
// import { cookies, COOKIE_NAMES } from '../../constants/cookies';

const initialState: UserState = {
    loggedIn: false,
    currentUser: undefined,
    loading: false
    // username: cookies.get( COOKIE_NAMES.username ),
    // loggedIn: cookies.get( COOKIE_NAMES.loggedIn ),
    // isAdminOf: cookies.get( COOKIE_NAMES.admin ),
    // portal: cookies.get( COOKIE_NAMES.admin ) ? cookies.get( COOKIE_NAMES.admin )[ 0 ] : null
};

type LoginParams = {
    username: string;
    password: string;
};

type CreateAccountParams = {
    user: User;
    password: string;
};

/**
 * Create the actions, reducers, and state for a user.
 * https://redux-toolkit.js.org/api/createSlice
 */
export const userSlice = createSlice( {
    name: 'user',
    initialState,
    reducers: {
        loading: ( state, action: PayloadAction<boolean> ) => {
            state.loading = action.payload;
        },
        logInUser: ( state, action: PayloadAction<User> ) => {
            state.loggedIn = true;
            state.currentUser = action.payload;
            // cookies.set( COOKIE_NAMES.loggedIn, true, { path: '/' } );
            // cookies.set( COOKIE_NAMES.username, action.payload.user, { path: '/' } );
            // cookies.set( COOKIE_NAMES.admin, action.payload.adminOf, { path: '/' } );
        },
        // logout: ( state ) => {
        //     state.loggedIn = false;
        //     state.currentUser = undefined;
        //     // cookies.remove( COOKIE_NAMES.username );
        //     // cookies.remove( COOKIE_NAMES.loggedIn );
        //     // cookies.remove( COOKIE_NAMES.admin );
        // }
    },
} );

export const { logInUser, loading } = userSlice.actions;

const userReducer = userSlice.reducer;

export default userReducer;

export type UserActionTypes =  ActionCreatorWithPayload<typeof userSlice.actions, string>;