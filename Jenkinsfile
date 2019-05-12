pipeline {
    agent any
    stages {
        stage('checkout') {
            steps {
               git clone "https://github.com/saidarao123/checkstyle.git"
                
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
                    }
        }
    }
}
