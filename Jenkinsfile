pipeline {
    agent any
    stages {
        stage('checkout') {
            steps {
               git clone "https://github.com/technovadors/MahendraProphecy.git"
                
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
                    }
        }
    }
}
