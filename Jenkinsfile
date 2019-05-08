pipeline {
    agent any
    stages {
        stage('checkout') {
            steps {
               git clone 'https://github.com/saidarao123/saidarepo.git'
            }
        }
        stage('build') {
            steps {
                sh "mvn package"
                    }
        }
    }
}
