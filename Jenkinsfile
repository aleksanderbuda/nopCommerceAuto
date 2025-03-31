pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/aleksanderbuda/nopCommerceAuto.git'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn clean test -DsuiteXmlFile=testng.xml -DthreadCount=4'
            }
        }
    }
    post {
        always {
            publishHTML(target: [
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/surefire-reports',
                reportFiles: 'emailable-report.html',
                reportName: 'TestNG Report'
            ])
            archiveArtifacts 'target/surefire-reports/**/*'
        }
    }
}