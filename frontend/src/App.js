import React, {Component} from 'react';
import './App.css';
import RestApp from "./RestApp";
import ListUsersComponent from "./component/ListUserComponent";

class App extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="container">
                <RestApp/>
                <ListUsersComponent/>
            </div>
        )
    }
}

export default App;
