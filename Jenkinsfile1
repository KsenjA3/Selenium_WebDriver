pipeline {

    agent any

    environment {
            mavenHome = tool name: 'maven jenkins', type: 'Maven'  // Указание пути к Maven
    }

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


                // Используем mavenHome для вызова Maven
                node {
                    script {
                        sh "${mavenHome}/bin/mvn clean test"
                    }
                }

            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Add deployment steps here (e.g., deploy to server)
            }
        }
        stage('Allure Report') {
                    steps {
                        node {
                            allure([
                                includeProperties: false,
                                jdk: '',
                                results: [[path: 'target/allure-results']]
                            ])
                        }
                    }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            // Add cleanup steps here

            node {
                junit 'target/surefire-reports/*.xml'
            }
        }

        success {
            echo 'Build and deployment succeeded!'
        }

        failure {
            echo 'Something went wrong!'
        }
    }
}