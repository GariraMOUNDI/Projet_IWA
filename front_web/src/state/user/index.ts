import { ActionCreatorWithPayload, createAsyncThunk, createSlice, } from '@reduxjs/toolkit';
import axios from 'axios';

import { User, UserState, UserSuccessTypes } from './types';
import apiconfig from '../../apiconfig';
import { isFulfilledAction, isPendingAction, isRejectedAction } from '../../globals';
import { RootState } from '../store';

//////////////////////////////////////////////////////////////
// ACTIONS
export const login = createAsyncThunk<{ user: User, confirmed: boolean; success: UserSuccessTypes; }, { username: string; password: string; }>( 'user/login',
    async ( { username, password }, thunkAPI ) => {
        const response = await axios.post( `${ apiconfig.baseUrl }/users/login`,
            {
                username,
                password
            } )
            .then( ( response ) => response.data )
            .catch( ( err ) => { console.log( err ); return { type: err.message }; } );


        if ( response.type === "USER_LOGGED_IN" ) {
            const user: User = {
                userId: response.payload.user_id,
                username: response.payload.username,
                firstName: response.payload.first_name,
                lastName: response.payload.last_name,
                email: response.payload.email,
                phoneNumber: response.payload.phone_number
            };
            return { user, confirmed: response.payload.enabled, success: response.type };
        } else return thunkAPI.rejectWithValue( response.type );
    }
);

export const createUser = createAsyncThunk<{ success: UserSuccessTypes; }, { user: User; password: string; confirmPassword: string; }>( 'user/createUser',
    async ( { user, password, confirmPassword }, thunkAPI ) => {
        const response = await axios.post( `${ apiconfig.baseUrl }/users/create`,
            {
                username: user.username,
                first_name: user.firstName,
                last_name: user.lastName,
                email: user.email,
                phone_number: user.phoneNumber,
                password,
                confirm_pass: confirmPassword,
            } )
            .then( ( response ) => { console.log( response ); return response.data; } )
            .catch( ( err ) => { console.log( err ); return { type: err.message }; } );



        if ( response.type === UserSuccessTypes.USER_CREATED )
            return { success: response.type };
        else return thunkAPI.rejectWithValue( response.type );
    }
);

export const confirmEmail = createAsyncThunk<{ success: UserSuccessTypes; }, string>( 'user/confirmEmail',
    async ( token, thunkAPI ) => {
        const response = await axios.get( `${ apiconfig.baseUrl }/users/confirmUser?token=${ token }`, {} )
            .then( ( response ) => { console.log( response ); return response.data; } )
            .catch( err => { console.log( err ); return { type: err.message }; } );



        if ( response.type === UserSuccessTypes.USER_CONFIRMED )
            return { success: response.type };
        else return thunkAPI.rejectWithValue( response.type );
    }
);

export const forgotPassword = createAsyncThunk<{ success: UserSuccessTypes; }, User>( 'user/forgotPassword',
    async ( user, thunkAPI ) => {
        const response = await axios.post( `${ apiconfig.baseUrl }/users/forgotUser`, { username: user.username, firstName: user.firstName, lastName: user.lastName, email: user.email, phoneNumber: user.phoneNumber } )
            .then( ( response ) => { console.log( response ); return response.data; } )
            .catch( err => { console.log( err ); return { type: err.message }; } );



        if ( response.type === UserSuccessTypes.FORGOT_EMAIL )
            return { success: response.type };
        else return thunkAPI.rejectWithValue( response.type );
    }
);

///////////////////////////////////////////////////////////////////////////
// STATE
const initialState: UserState = {
    loggedIn: false,
    confirmed: false,
    loading: false,
    currentUser: undefined,
    token: undefined,
    error: undefined,
    success: undefined,
};

export const userSlice = createSlice( {
    name: 'user',
    initialState,
    reducers: {
        logout: ( state ) => {
            state.loggedIn = false;
            state.currentUser = undefined;
            state.error = undefined;
        },
        removeError: ( state ) => {
            state.error = undefined;
        }
    },
    extraReducers: builder => {
        builder
            .addCase( login.fulfilled, ( state, action ) => {
                console.log( action.payload );
                state.loggedIn = true;
                state.currentUser = action.payload.user;
                state.confirmed = action.payload.confirmed;
            } )
            .addCase( createUser.fulfilled, ( state, action ) => {
                console.log( action.type );
            } )
            .addCase( confirmEmail.fulfilled, ( state, action ) => {
                console.log( action.type );
            } )
            .addCase( forgotPassword.fulfilled, ( state, action ) => {
                console.log( action.type );
            } )
            .addMatcher( isPendingAction( "user/" ), ( state, action ) => {
                console.log( action.type );
                state.loading = true;
                state.error = undefined;
                state.success = undefined;
            } )
            .addMatcher( isRejectedAction( "user/" ), ( state, action ) => {
                console.log( action.type );
                state.loading = false;
                state.error = action.payload;
                state.success = undefined;
            } )
            .addMatcher( isFulfilledAction( "user/" ), ( state, action ) => {
                console.log( action.type );
                state.loading = false;
                state.error = undefined;
                state.success = action.payload.success;
            } );
    }
} );

const userReducer = userSlice.reducer;

export const { logout, removeError } = userSlice.actions;

export const loggedIn = ( state: RootState ) => state.user.loggedIn;

export const currentUser = ( state: RootState ) => state.user.currentUser;

export const loading = ( state: RootState ) => state.user.loading;

export const error = ( state: RootState ) => state.user.error;

export const success = ( state: RootState ) => state.user.success;

export default userReducer;
