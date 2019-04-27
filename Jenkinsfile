node ('master'){
    stage('initialize'){
        def mvn_path="/usr/share/maven/bin"
        def WORKSPACE=pwd()
        echo "${WORKSPACE}"
        echo "===================="
    }

 stage('checkout') {
     checkout ([$class: 'GitSCM',
     branches: [[name: '*/master']],
     doGenerateSubmoduleConfigurations: false,
     extensions:[], submoduleCfg: [],
     userRemoteConfigs: [[url: 'https://github.com/viswanathkatta/mahendra.git']]]) 
     
 }
 
 stage('build'){
     sh 'cd /usr/share/maven/bin -f  ${WORKSPACE}/mahendra/pom.xml clean package'
  }
}
