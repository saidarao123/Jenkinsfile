pipeline {
    agent any
    
    
    stages {
        stage('clone') {
            git url: 'https://github.com/saidarao123/nexus.git'
        }
      
        stage('build') {
            steps {
                sh 'pwd&&mvn clean package '
                    }
        }
    } 
}
