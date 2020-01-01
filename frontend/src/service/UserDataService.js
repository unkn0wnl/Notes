import axios from 'axios';

const REST_API_URL = 'http://localhost:5000/api';

class UserDataService {
    retrieveAllUsers(name) {
        return axios.get(`${REST_API_URL}/getAll`);
    }

    deleteUser(id) {
        return axios.delete(`${REST_API_URL}/users/${id}`);
    }
}

export default new UserDataService()