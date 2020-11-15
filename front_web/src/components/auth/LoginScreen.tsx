import { Box, Grid, Form, FormField, TextInput, Button } from 'grommet';
import React, { useState } from 'react';

export default () => {
    const [ username, setUsername ] = useState( '' );
    const [ password, setPassword ] = useState( '' );

    return (
        <Box animation={{ type: "slideLeft", duration: 300 }
        }>
            <Form
                onSubmit={() => { console.log( username, password ); }}
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