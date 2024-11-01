pipeline {
    agent any
    stages {
        stage('Hello') {
            steps {
                script {
                if (isUnix()) {
                      sh 'mvn test'
                    } else {
                      bat 'mvn test'
                    }
                }
            }
        }
    }
}
