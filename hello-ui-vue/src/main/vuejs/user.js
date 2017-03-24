import Vue from "vue";
import {isString, contains} from "underscore";

class User {

    constructor() {
        this.name = null;
        this.roles = null;
    }

    fetchData() {
        let self = this;
        Vue.http.get('/api/security/user').then(function (res) {
            self.name = res.data.name;
            self.roles = res.data.roles;
        });
    }

    get isAuthenticated() {
        return isString(this.name);
    }

    get isAdmin() {
        return contains(this.roles, 'ROLE_ADMIN');
    }

}

let user = new User();

export default user;