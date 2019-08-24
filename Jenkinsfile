pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn --version && cd comparison-shopp && mvn clean package'
            }
        }
    }
}
