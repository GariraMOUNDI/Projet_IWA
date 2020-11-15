import { Box, Form, FormField, TextInput, Button } from 'grommet';
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { login } from '../../state/user/queries';

export default () => {
    const [ username, setUsername ] = useState( '' );
    const [ password, setPassword ] = useState( '' );
    const dispatch = useDispatch();

    return (
        <Box animation={{ type: "slideLeft", duration: 300 }
        }>
            <Form
                onSubmit={() => { dispatch( login( { username, password } ) ); }}
                onReset={() => { setUsername( '' ); setPassword( '' ); }}
            >
                <FormField name="username" label="Pseudo">
                    <TextInput id="text-input-username" name="username" value={username} onChange={ev => setUsername( ev.target.value )} />
                </FormField>
                <FormField name="password" label="Mot de passe">
                    <TextInput id="text-input-password" name="password" value={password} onChange={ev => setPassword( ev.target.value )} />
                </FormField>
                <Box direction="row" gap="medium">
                    <Button type="submit" primary label="Connexion" disabled={username === '' || password === ''} />
                    <Button type="reset" label="Reset" />
                </Box>
            </Form>
        </Box > );
};