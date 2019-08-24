pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn --version && cd comparacao-de-precos && mvn clean package'
            }
        }
    }
}
