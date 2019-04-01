node {
    stage("build") {
        docker.image("jhipster/jhipster:latest").inside("-v /home/vunt/maven/.m2:/root/.m2") { c ->
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
    }
    
    def dockerImage
    stage('build docker') {
        sh "cp -R src/main/docker target/"
        sh "cp target/*.war target/docker/"
        if (env.BRANCH_NAME == 'development') {
            echo 'I only execute on the development branch'
            dockerImage = docker.build('snv-development/service-common', 'target/docker')
        } else if (env.BRANCH_NAME == 'production') {
            echo 'I only execute on the production branch'
            dockerImage = docker.build('snv-production/service-common', 'target/docker')
        }
    } 
}

