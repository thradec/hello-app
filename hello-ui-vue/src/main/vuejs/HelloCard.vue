<template>

    <div class='container'>
        <div class='row'>
            <div class='col s6 offset-s3'>
                <div class='card grey lighten-4'>
                    <div class='card-content'>
                        <h1 id="actual-hello" class='center-align' style='font-weight: 900'>{{message}}</h1>
                    </div>
                    <div class='card-action'>
                        <button id="next-hello" class='btn blue waves-effect waves-light'
                                @click.prevent='getRandomHello'><i class='material-icons right'>replay</i>next</button>
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
            message: '...'
        };
    },
    methods: {
        getRandomHello: function (event) {
            let self = this;
            self.$http.get('/api/hello/random').then(function (response) {
                self.message = response.data.message;
            });
        }
    },
    created: function () {
        this.getRandomHello();
    }
}

</script>