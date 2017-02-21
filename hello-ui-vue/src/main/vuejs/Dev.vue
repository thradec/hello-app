<template>

    <div class='container'>
        <div class='row'>
            <div class='col s6 offset-s3'>

                <div class='collection'>
                    <a href='/dev/info' class="collection-item">
                        <b class='grey-text text-darken-2'>/info</b>
                        <span class='grey-text'>(shows arbitrary application info)</span>
                    </a>
                    <a href='/dev/health' class="collection-item">
                        <b class='grey-text text-darken-2'>/health</b>
                        <span class='grey-text'>(shows application health information)</span>
                    </a>
                    <a href='/dev/flyway' class="collection-item">
                        <b class='grey-text text-darken-2'>/flyway</b>
                        <span class='grey-text'>(shows database migrations that have been applied)</span>
                    </a>
                    <a href='/dev/mappings' class="collection-item">
                        <b class='grey-text text-darken-2'>/mappings</b>
                        <span class='grey-text'>(shows list of all mapping paths)</span>
                    </a>
                    <a href='/dev/env' class="collection-item">
                        <b class='grey-text text-darken-2'>/env</b>
                        <span class='grey-text'>(shows properties from environment)</span>
                    </a>

                </div>

            </div>
        </div>
    </div>

</template>

<script>

export default {
    name: 'dev',
}

</script>