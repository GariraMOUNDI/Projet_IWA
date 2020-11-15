import { Box, Button, Form, FormField, TextInput } from 'grommet';
import React, { useState } from 'react';

export default () => {
    const [ username, setUsername ] = useState( '' );
    const [ firstName, setFirstName ] = useState( '' );
    const [ lastName, setLastName ] = useState( '' );
    const [ password, setPassword ] = useState( '' );
    const [ confirmPassword, setConfirmPassword ] = useState( '' );
    const [ email, setEmail ] = useState( '' );
    return (
        <Box animation={{ type: "slideLeft", duration: 300 }}>
            <Form
                onSubmit={() => { console.log( username, password ); }}
                onReset={() => { setUsername( '' ); setPassword( '' ); }}>
                <FormField name="username" label="Pseudo">
                    <TextInput id="text-input-username" name="username" value={username} onChange={ev => setUsername( ev.target.value )} />
                </FormField>
                <FormField name="firstName" label="Prénom">
                    <TextInput id="text-input-firstName" name="firstName" value={firstName} onChange={ev => setFirstName( ev.target.value )} />
                </FormField>
                <FormField name="lastName" label="Nom">
                    <TextInput id="text-input-firstName" name="lastName" value={lastName} onChange={ev => setLastName( ev.target.value )} />
                </FormField>
                <FormField name="email" label="Adresse mail">
                    <TextInput id="text-input-email" name="email" value={email} onChange={ev => setEmail( ev.target.value )} />
                </FormField>
                <FormField name="password" label="Mot de passe">
                    <TextInput id="text-input-password" name="password" value={password} onChange={ev => setPassword( ev.target.value )} />
                </FormField>
                <FormField name="confirmPassword" label="Confirmer le mot de passe">
                    <TextInput id="text-input-confirm-password" name="confirmPassword" value={confirmPassword} onChange={ev => setConfirmPassword( ev.target.value )} type="password" />
                </FormField>
                <Box direction="row" gap="medium">
                    <Button type="submit" primary label="Connexion" disabled={username === '' || password === ''} />
                    <Button type="reset" label="Reset" />
                </Box>
            </Form>
        </Box> );
};