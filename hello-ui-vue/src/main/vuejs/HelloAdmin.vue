<template>

    <div class='container'>
        <div class='row'>
            <div class='col s6 offset-s3'>
                <div>
                    <h4>Administration</h4>

                    <table class='bordered highlight'>
                        <tbody>
                        <tr v-if='user.isAuthenticated'>
                            <td>#</td>
                            <td>
                                <input placeholder='new greeting ...' type='text' v-model='newHello.message' @keyup.enter='createHello'>
                            </td>
                            <td class="col-btn">
                                <a role='btn-create' class='btn-floating waves-effect waves-light green'
                                   @click.prevent='createHello' :disabled='!createHelloEnabled'><i class='material-icons'>add</i></a>
                            </td>
                        </tr>
                        <tr v-for='hello in hellos'>
                            <td>#{{hello.id}}</td>
                            <td>{{hello.message}}</td>
                            <td class="col-btn">
                                <a v-if='user.isAdmin'
                                   role='btn-delete' class='btn-floating waves-effect waves-light red'
                                   @click.prevent='deleteHello(hello.id)'><i class='material-icons'>delete</i></a>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>

</template>

<script>

import user from './user';

export default {
    name: 'hello-admin',

    data() {
        return {
            user: user,
            newHello: {
                message: ''
            },
            hellos: []
        };
    },

    computed: {

        createHelloEnabled: function() {
            return this.newHello.message.length > 0;
        }

    },

    methods: {

        getHellos: function() {
            let self = this;
            self.$http.get('/api/hello').then(function(res) {
                self.hellos = res.data;
            });
        },

        createHello: function() {
            if( !this.createHelloEnabled ) {
                return;
            }
            let self = this;
            self.$http.post('/api/hello', self.newHello)
                .then(function(res) {
                    self.newHello.message = '';
                    self.getHellos();
                }, function(err) {
                    alert(JSON.stringify(err));
                });
        },

        deleteHello: function(id) {
            let self = this;
            self.$http.delete('/api/hello/'+id)
                .then(function(res) {
                    self.getHellos();
                }, function(err) {
                    alert(JSON.stringify(err));
                });
        }

    },

    mounted: function() {
        this.getHellos();
    }
}


</script>

<style>

tr {
    height: 72px;
}

td.col-btn {
    width: 72px;
}

tr input[type=text],
tr input[type=text]:focus:not([readonly]) {
    border-bottom: none;
    box-shadow: none;
    margin: 0;
}

tr a[role=btn-delete] {
    display: none;
}

tr:hover a[role=btn-delete] {
    display: inline-block;
}

</style>