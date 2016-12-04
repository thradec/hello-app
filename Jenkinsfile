node {
    stage('Preparation') {
        git 'https://github.com/thradec/hello-app'
        sh 'chmod a+x ./mvnw'
    }
    stage('Build') {
        sh './mvnw compile'
    }
    stage('Test') {
        sh './mvnw test'
    }
    stage('Results') {
        junit '**/target/surefire-reports/TEST-*.xml'
        archive 'target/*.jar'
    }
}