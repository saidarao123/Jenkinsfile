pipeline {
    agent any
    script {
        def pms = [
            string(name:'repo',defaultvalue:"", description:""),
            ]
    }
    
    stages {
      
        stage('build') {
            steps {
                sh 'mvn clean package '
                    }
        }
    } 
}
