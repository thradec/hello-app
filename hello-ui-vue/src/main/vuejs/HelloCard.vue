<template>

    <div class='card grey lighten-4'>
        <div class='card-content'>
            <h1 class='center-align' style='font-weight: 900'>{{greeting}}</h1>
        </div>
        <div class='card-action'>
            <a href='#' class='waves-effect waves-light btn blue' v-on:click='getGreeting'>
                <i class='material-icons right'>replay</i>next
            </a>
        </div>
    </div>

</template>

<script>

export default {
    name: 'hello-card',
    data() {
        return {
            greeting: '...'
        };
    },
    methods: {
        getGreeting: function (event) {
            let self = this;
            self.$http.get('/api/hello/random').then(function (response) {
                self.greeting = response.data.message;
            });
        }
    },
    created: function () {
        this.getGreeting();
    }
}

</script>