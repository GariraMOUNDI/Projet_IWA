import React, { useState } from 'react';
import { Box, Button, Heading, Grommet, Collapsible, ResponsiveContext, Layer } from 'grommet';
import { FormClose, Notification } from 'grommet-icons';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import LoginScreen from './components/auth/LoginScreen';
import MainScreen from './components/MainScreen';
import CreateAccountScreen from './components/auth/CreateAccountScreen';
import Navbar from './components/Navbar';

const theme = {
  global: {
    colors: {
      brand: '#22dce6'
    },
    font: {
      family: 'Roboto',
      size: '18px',
      height: '20px',
    },
  },
};



export default () => {

  // const [ showSidebar, setShowSidebar ] = useState( false );
  return (
    <Router>
      <Grommet {...{ theme }} full>
        <Box fill>
          <Navbar />
          <Box direction='row' flex overflow={{ horizontal: 'hidden' }}>
            <Box flex align='center' justify='center'>
              <Switch >
                <Route exact path="/" component={MainScreen} />
                <Route path="/login" component={LoginScreen} />
                <Route path="/createAccount" component={CreateAccountScreen} />
              </Switch>
            </Box>
          </Box>
        </Box>
      </Grommet >
    </Router>
  );
};

