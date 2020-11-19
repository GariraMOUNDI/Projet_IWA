import { Box, Button, Heading, Paragraph } from 'grommet';
import { FormNext } from 'grommet-icons';
import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { confirmEmail, loading } from '../../state/user';

export default ( { match }: RouteComponentProps<{ token?: string; }> ) => {
    const dispatch = useDispatch();
    const inProgress = useSelector( loading );

    useEffect( () => {
        match.params.token !== undefined && dispatch( confirmEmail( match.params.token ) );
    }, [ match.params.token, dispatch ] );

    return (
        <Box>
            {inProgress ?
                <Paragraph>Nous confirmons votre compte</Paragraph>
                :
                <Box>
                    <Heading>Votre compte est confirmé !</Heading>
                    <Link to="/login">
                        <Button primary label="Connexion" hoverIndicator icon={<FormNext />} />
                    </Link>
                </Box>
            }
        </Box>
    );

};