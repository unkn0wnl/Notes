import React, {Component} from "react";
import UserDataService from "../service/UserDataService";

class ListUserComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: [],
            message: null,
            page: 1
        };
        // this.refreshUsers = this.refreshUsers().bind(this);
    }

    componentDidMount() {
        this.refreshUsers();
    }

    refreshUsers() {
        UserDataService.retrieveAllUsers()
                .then(
                        response => {
                            console.log(response);
                            this.setState({users: response.data})
                        }
                )
    }

    deleteUser(id) {
        UserDataService.deleteUser(id)
                .then(response => {
                    this.setState({message: 'User was successfully deleted.'});
                    this.refreshUsers();
                })
    }

    render() {
        console.log(this.state.users);
        return (
                <div className="container">
                    <h3>All Users</h3>
                    {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                    <div className="container">
                        <table className="table">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Username</th>
                                <th>E-mail</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.users.map(
                                        user => <tr key={user.id}>
                                            <td>{user.id}</td>
                                            <td>{user.name}</td>
                                            <td>{user.email}</td>
                                            <td>
                                                <button className="btn btn-warning"
                                                        onClick={() => this.deleteUser(user.id)}>Delete</button>
                                            </td>
                                        </tr>
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
        )
    }
}

export default ListUserComponent