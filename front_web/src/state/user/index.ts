import { Action, ActionCreatorWithPayload, CaseReducerActions, createAsyncThunk, createSlice, PayloadAction } from '@reduxjs/toolkit';
import { User, UserState } from './types';
import { login } from './queries';


const initialState: UserState = {
    loggedIn: false,
    currentUser: undefined,
    loading: false
};

export const userSlice = createSlice( {
    name: 'user',
    initialState,
    reducers: {
        // loading: ( state, action: PayloadAction<boolean> ) => {
        //     state.loading = action.payload;
        // },
        // logInUser: ( state, action: PayloadAction<string> ) => {
        //     console.log( 'ass', action.payload );
        //     state.loggedIn = true;
        //     // state.currentUser = action.payload;
        //     // cookies.set( COOKIE_NAMES.loggedIn, true, { path: '/' } );
        //     // cookies.set( COOKIE_NAMES.username, action.payload.user, { path: '/' } );
        //     // cookies.set( COOKIE_NAMES.admin, action.payload.adminOf, { path: '/' } );
        // },
        // logout: ( state ) => {
        //     state.loggedIn = false;
        //     state.currentUser = undefined;
        //     // cookies.remove( COOKIE_NAMES.username );
        //     // cookies.remove( COOKIE_NAMES.loggedIn );
        //     // cookies.remove( COOKIE_NAMES.admin );
        // }
    },
    extraReducers: builder => {
        builder.addCase( login.fulfilled, ( state, action ) => {
            state.loggedIn = true;
            state.currentUser = action.payload;
        } );

    }
} );

const userReducer = userSlice.reducer;

export default userReducer;

export type UserActionTypes = ActionCreatorWithPayload<typeof userSlice.actions, string>;