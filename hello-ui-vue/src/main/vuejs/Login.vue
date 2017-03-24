<template>

    <div class='container'>
        <div class='row'>
            <div class='col s6 offset-s3'>
                <div class='card grey lighten-4'>
                    <div class='card-content'>
                        <div class='container'>
                            <form action='/login' method='post' @submit.prevent='onSubmit'>
                                <div class='row'>
                                    <div class="input-field">
                                        <input type='text' id='username' name='username'/>
                                        <label for='username'>Username</label>
                                    </div>
                                </div>
                                <div class='row'>
                                    <div class="input-field">
                                        <input type='password' id='password' name='password'/>
                                        <label for='password'>Password</label>
                                    </div>
                                </div>
                                <div class='row'>
                                    <div class="col s3 offset-s9">
                                        <button type='submit' class='btn blue waves-effect waves-light'>Submit</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>

import user from './user';

export default {
    name: 'login',

    methods: {

        onSubmit: function(e) {
            let self = this;
            let formData = new FormData(e.target);
            self.$http.post('/login', formData)
                .then(function() {
                    user.fetchData();
                })
                .then(function() {
                    self.$router.push('/');
                });
        }

    }
}

</script>