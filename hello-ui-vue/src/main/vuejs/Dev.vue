<template>

    <div class='container'>
        <div class='row'>
            <div class='col s6 offset-s3'>

                <table class='bordered'>
                    <tbody>
                    <tr>
                        <td>
                            <a href='/actuator/info'>/info <span class='grey-text'>(shows arbitrary application info)</span></a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href='/actuator/health'>/health <span class='grey-text'>(shows application health information)</span></a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href='/actuator/flyway'>/flyway <span class='grey-text'>(shows database migrations that have been applied)</span></a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href='/actuator/mappings'>/mappings <span class='grey-text'>(shows list of all mapping paths)</span></a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href='/actuator/env'>/env <span class='grey-text'>(shows properties from environment)</span></a>
                        </td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </div>

</template>

<script>

export default {
    name: 'dev',
}

</script>