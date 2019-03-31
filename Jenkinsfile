pipeline {
  agent {
    docker {
      image 'maven:3.6-jdk-8-alpine'
      args '-v /home/vunt/maven/.m2:/root/.m2'
    }

  }
  stages {
    stage('Package') {
      steps {
        sh './mvnw -Pprod clean package'
      }
    }
    
    stage('Deliver for development') {
      when {
        branch 'development'
      }
      steps {
        sh './jenkins/scripts/deliver-for-development.sh'
        input 'Finished using the web site? (Click "Proceed" to continue)'
        sh './jenkins/scripts/kill.sh'
      }
    }
    stage('Deploy for production') {
      when {
        branch 'production'
      }
      steps {
        sh './jenkins/scripts/deploy-for-production.sh'
        input 'Finished using the web site? (Click "Proceed" to continue)'
        sh './jenkins/scripts/kill.sh'
      }
    }
  }
}
