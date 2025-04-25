
// CODE_CHANGES = getGitChanges ()
pipeline {

    agent any

   parameters {
      //string (name: 'VERSION', defaultValue: '', description: 'version to deploy on prod')
      choice (name: 'VERSION', choices: ['1.1.1', '5.5.5', '9.9.9'], description: 'version to deploy on prod')
      booleanParam(name: 'executeTests', defaultValue: true, description: 'version to deploy' )
   }



    environment {
        NEW_VERSION = '9.9.9'
        //SERVER_CREDENTIALS = cledentials('jenkins_id_global_cledentials')
    }

    tools {
            maven 'maven jenkins'  // Укажите вашу версию Maven
            //gradle
            //jdk
        }



    stages {
        stage('Build') {
//             when {
//                 expression {
//                     BRANCH_NAME == 'work' && CODE_CHANGES == true
//                 }
//             }

            steps {
                echo 'Building project...'
                echo "Building my version ${NEW_VERSION}"
                // Add build steps here (e.g., mvn clean install)
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                // Add test execution steps here (e.g., mvn test)

                when {
                     expression {
                           params.executeTests ==true
                     }
                }
                // Используем mavenHome для вызова Maven
                sh 'mvn clean test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                echo "Deploying version ${params.VERSION}"

//                 echo "Deploying with ${SERVER_CREDENTIALS}"
//                 withCredentials ([
//                     usernamePassword (credentials: 'myname', usernameVariable: USER, passwordVariable : PWD)
//                 ]) {
//                     sh " some script ${USER} ${PWD}"
//                 }
                // Add deployment steps here (e.g., deploy to server)
            }
        }

        stage('Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            // Add cleanup steps here

            junit 'target/surefire-reports/*.xml'
        }

        success {
            echo 'Build and deployment succeeded!'
        }

        failure {
            echo 'Something went wrong!'
        }
    }
}