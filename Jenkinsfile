pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'cd comparison-shopp && mvn --version'
            }
        }
    }
}
