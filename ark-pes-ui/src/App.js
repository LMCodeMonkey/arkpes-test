import Clients from './Clients';
import ClientDetails from './ClientDetails';
import { Switch, Route, BrowserRouter as Router } from "react-router-dom";
import { Col, Row } from 'antd';
import './App.css';

function App() {
  return (
    <div>
      <Row justify='center'>
        <Col span={24} >
          <div className='client_header'>
            <h1>Welcome!</h1>
          </div>
        </Col>
      </Row>
      <Row justify='center'>
        <Col>
        <Router>
          <Switch>
            <Route exact path='/' component={ Clients }/>
            <Route path='/clients' component={ Clients }/>
            <Route path='/clientDetails' component={ ClientDetails }/>
          </Switch>
        </Router>
        </Col>
      </Row>
    </div>
  );
}

export default App;
