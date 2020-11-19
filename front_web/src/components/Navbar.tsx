import React from 'react';
import { Box, Heading } from 'grommet';
import { useDispatch, useSelector } from 'react-redux';
import { loggedIn, logout } from '../state/user';
import { PowerShutdown } from 'grommet-icons';

const AppBar = ( props: any ) => (
    <Box
        tag='header'
        direction='row'
        align='center'
        justify='between'
        background='brand'
        pad={{ left: 'medium', right: 'small', vertical: 'small' }}
        // elevation='medium'
        style={{ zIndex: '1' }}
        {...props}
    />
);

export default () => {

    const connected = useSelector( loggedIn );
    const dispatch = useDispatch();

    return (
        <AppBar>
            <Heading level='3' margin='none'>
                Alerte Covid !
            </Heading>
            {connected && <Box
                hoverIndicator={true}
                background="brand2"
                round
                pad="xsmall"
                onClick={() => dispatch( logout() )}>
                <PowerShutdown />
            </Box>}
        </AppBar> );
};