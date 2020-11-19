import { Box, Layer, Text } from 'grommet';
import React from 'react';
import { useSelector } from 'react-redux';

import { loading as user } from '../state/user';
import { loading as location } from '../state/location';

export default () => {

    const inProgressUser = useSelector( user );
    const inProgressLocation = useSelector( location );

    return (
        <Box>
            {( inProgressUser || inProgressLocation ) && <Layer
                animation="slide"
                position="top"
                plain>
                <Box pad="medium" background="brand2" round margin={{ vertical: "xlarge" }}>
                    <Text>Loading</Text>
                </Box>
            </Layer>
            }
        </Box>
    );
};