pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building project...'
                // Add build steps here (e.g., mvn clean install)
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Add test execution steps here (e.g., mvn test)
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Add deployment steps here (e.g., deploy to server)
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            // Add cleanup steps here
        }
        success {
            echo 'Build and deployment succeeded!'
        }
        failure {
            echo 'Something went wrong!'
        }
    }
}