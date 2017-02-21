<template>

    <div class='container'>
        <div class='row'>
            <div class='col s6 offset-s3'>
                <div class='card grey lighten-4'>
                    <div class='card-content'>
                        <h1 class='center-align' style='font-weight: 900'>{{greeting}}</h1>
                    </div>
                    <div class='card-action'>
                        <button class='btn blue waves-effect waves-light' v-on:click.prevent='getGreeting'>
                            <i class='material-icons right'>replay</i>next
                        </button>
                    </div>
                </div>
            </div>
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