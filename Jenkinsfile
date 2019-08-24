pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('build') {
            steps {
                sh 'cd comparison-shopp && mvn --version'
            }
        }
    }
}
