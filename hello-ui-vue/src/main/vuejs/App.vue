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
                    <ul class='right hide-on-med-and-down'>
                        <li>
                            <router-link to='/hello/admin' title='Administration'>
                                <i class='material-icons'>playlist_add</i>
                            </router-link>
                        </li>
                        <li>
                            <router-link to='/dev' title='Development zone'>
                                <i class='material-icons'>info_outline</i>
                            </router-link>
                        </li>
                        <li v-if='user.isAuthenticated'>
                            <a href='/logout' :title='"Logout " + user.name'>
                                <i class='material-icons left'>exit_to_app</i>Logout
                            </a>
                        </li>
                        <li v-else>
                            <router-link to='/login' title='Login'>
                                <i class='material-icons left'>account_circle</i>Login
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

import user from './user';

export default {
    name: 'app',
    data() {
        return {
            user: user
        };
    }
}

</script>

<style>

#app-logo {
    padding-left: 20px;
}

</style>