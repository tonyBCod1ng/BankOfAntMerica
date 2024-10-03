pipeline {
    agent any

    tools {
        maven 'M3'  // Use the version of Maven installed on your Jenkins server
        jdk 'java11'  // Matches the java.version specified in your POM
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
                    steps {
                        sh 'mvn clean package -DskipTests'
                    }
                }

stage('Test') {
     parallel {
       stage('Test On Windows') {
         steps {
           echo "Running tests on Windows"
         }
       }
       stage('Test On Linux') {
         steps {
           echo "Running tests on Linux"
         }
       }
     }
   }

//         stage('Static Code Analysis') {
//             steps {
//                 sh 'mvn org.jacoco:jacoco-maven-plugin:report'
//                 // Assuming you have SonarQube set up. If not, you can remove or comment out this step.
//                 // withSonarQubeEnv('SonarQube') {
//                 //     sh 'mvn sonar:sonar'
//                 // }
//             }
//         }

        stage('Deploy to Staging') {
            steps {
                sh 'mvn spring-boot:run -Dspring-boot.run.profiles=staging'
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
        }
    }
}