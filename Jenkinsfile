#!/usr/bin/env groovy

node {
    stage('Checkout') {
        git url: 'https://github.com/devhvm/snv_common.git', branch: 'development'
    }

    docker.image('jhipster/jhipster:v5.8.2').inside('-u jhipster -e MAVEN_OPTS="-Duser.home=./"') {
        stage('check java') {
            sh "java -version"
        }

        stage('clean') {
            sh "chmod +x mvnw"
            sh "./mvnw clean"
        }

        stage('backend tests') {
            try {
                sh "./mvnw test"
            } catch(err) {
                throw err
            } finally {
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }

        stage('packaging') {
            sh "./mvnw verify -Pprod -DskipTests"
            archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
        }
    }

    def dockerImage
    stage('build docker') {
        sh "cp -R src/main/docker target/"
        sh "cp target/*.war target/docker/"
        dockerImage = docker.build('snv/common', 'target/docker')
    }

    stage('publish docker') {
        docker.withRegistry('http://vtools.xyz:5050', 'snv') {
            dockerImage.push 'latest'
        }
    }
}
