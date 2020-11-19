import React from 'react';
import { Box, Grommet } from 'grommet';
import { BrowserRouter as Router, Redirect, Route, Switch } from 'react-router-dom';
import LoginScreen from './components/user/LoginScreen';
import WelcomeScreen from './components/WelcomeScreen';
import CreateAccountScreen from './components/user/CreateAccountScreen';
import Navbar from './components/Navbar';
import MainScreen from './components/MainScreen';
import UserError from './components/Error';
import { useSelector } from 'react-redux';
import { loggedIn } from './state/user';
import ConfirmAccountScreen from './components/user/ConfirmAccountScreen';
import UserLoading from './components/Loading';
import { Colors } from './globals';
import ForgotPasswordScreen from './components/user/ForgotPasswordScreen';

const theme = {
  global: {
    colors: { ...Colors },
    font: {
      family: 'Roboto',
      size: '18px',
      height: '20px',
    },
  },
};



export default () => {

  const connected = useSelector( loggedIn );
  // const [ showSidebar, setShowSidebar ] = useState( false );
  return (
    <Router>
      <Grommet {...{ theme }} full>
        <Box fill >
          <Navbar />
          <Box direction='row' flex overflow={{ horizontal: 'hidden' }}>
            <Box flex align='center' justify='center'>
              <UserLoading />
              <UserError />
              {!connected ? <Redirect to="/welcome" /> : <Redirect to="/" />}
              <Switch >
                <Route exact path="/" component={MainScreen} />
                <Route path="/welcome" component={WelcomeScreen} />
                <Route path="/login" component={LoginScreen} />
                <Route path="/createAccount" component={CreateAccountScreen} />
                <Route path="/confirmAccount/:token" component={ConfirmAccountScreen} />
                <Route path="/forgot" component={ForgotPasswordScreen} />
              </Switch>
            </Box>
          </Box>
        </Box>
      </Grommet >
    </Router>
  );
};

