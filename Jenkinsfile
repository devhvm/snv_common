pipeline {
    agent any
    environment {
        CI = 'true'
    }
    stages {
        stage('Check java') {
            steps { sh "java -version"}
        }
        stage('Clean') {
            steps {
                sh "chmod +x mvnw"
                sh "./mvnw clean"
            }    
        }
        stage('backend tests') {
            steps {
                script {
                    try {
                        sh "./mvnw test"
                    } catch(err) {
                        throw err
                    } finally {
                        junit '**/target/surefire-reports/TEST-*.xml'
                    }
                }
            }
        }
        stage('packaging') {
            steps {
                sh "./mvnw verify -Pprod -DskipTests"
                archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
            }
        }

        stage('Deliver for development') {
            when {
                branch 'development' 
            }
            steps {
                echo 'Starting to build docker image'
    
                script {
                    sh "cp -R src/main/docker target/"
                    sh "cp target/*.war target/docker/"
                    def dockerImage = docker.build('snv/service-common-dev', 'target/docker')
                }
            }
        }
        stage('Deploy for production') {
            when {
                branch 'production'  
            }
            steps {
                echo 'Starting to build docker image'
    
                script {
                    sh "cp -R src/main/docker target/"
                    sh "cp target/*.war target/docker/"
                    def dockerImage = docker.build('snv/service-common', 'target/docker')
                }
            }
        }
    }
}
