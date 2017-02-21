<template>

    <div id='app'>
        <header>
            <nav>
                <div class='nav-wrapper grey darken-3'>
                    <span id='app-logo'>
                        <router-link to='/' class='app-name brand-logo'>
                            <i class='material-icons'>code</i>hello-app
                        </router-link>
                    </span>
                    <ul class='right'>
                        <li>
                            <router-link to='/dev' title='Links to Actuator endpoints'>
                                <i class='material-icons'>info_outline</i>
                            </router-link>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <main>
            <router-view></router-view>
        </main>

    </div>

</template>

<script>

export default {
    name: 'app',
}

</script>

<style>

#app-logo {
    padding-left: 20px;
}

</style>