pipeline {
  agent {
    docker {
      image 'maven:3.6-jdk-8-alpine'
      args '-v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh './mvnw -Pprod -DskipTests clean package'
      }
    }
    stage('Test') {
      steps {
        sh './mvnw clean test'
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
    stage('End') {
      steps {
        echo 'ok'
      }
    }
  }
}
