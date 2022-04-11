pipeline {
    agent any
    script {
        def pms = [
            string(name:'repo',defaultvalue:"", description:""),
            ]
    }
    
    stages {
        stage('checkout') {
            steps {
               #git clone $repo
                }
        }
        stage('build') {
            steps {
                sh 'mvn clean package '
                    }
        }
    } 
}
