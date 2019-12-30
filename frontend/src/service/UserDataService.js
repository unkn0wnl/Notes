import axios from 'axios';

const REST_API_URL = 'http://localhost:5000/api';

class UserDataService {
    retrieveAllUsers(name) {
        return axios.get(`${REST_API_URL}/getAll`);
    }
}

export default new UserDataService()