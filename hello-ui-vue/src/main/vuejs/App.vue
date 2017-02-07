<template>

    <div id='app'>
        <header>
            <nav>
                <div class='nav-wrapper grey darken-3'>
                    <span id='app-logo'>
                        <a href='#' class='app-name brand-logo'>
                            <i class='material-icons'>code</i>hello-app
                        </a>
                    </span>
                </div>
            </nav>
        </header>

        <main>
            <div class='container'>
                <div class='row'>
                    <div class='col s6 offset-s3'>
                        <hello-card></hello-card>
                    </div>
                </div>
            </div>
        </main>

    </div>

</template>

<script>

import HelloCard from './HelloCard.vue'

export default {
    name: 'app',
    components: {
        'hello-card': HelloCard
    }
}

</script>

<style>

#app-logo {
    padding-left: 20px;
}

</style>