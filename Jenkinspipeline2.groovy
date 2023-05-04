pipeline{
    agent any
    environment {
        DIRECTORY_PATH="C:/Program Files/Jenkins"
        TESTING_ENVIRONMENT="QA"
        PRODUCTION_ENVIRONMENT="GAYATRI"
    }
    stages{
        stage('Build'){
            steps{
                echo "fetching the source code from the directory: $DIRECTORY_PATH "
                echo "compile the code and generate any necessary artifacts ......"
            }
        }
        stage('Unit and Integration Tests'){
            steps{
                echo "unit test is running in this stage.. "
                echo "integration tests are running ....! "
            }
        }
        stage('Code Analysis'){
            steps{
                echo "Analyse Code...."
            }
        }
        stage('Security Scan'){
            steps{
                echo "Security Scan on: $TESTING_ENVIRONMENT"
            }
            post{
                success{
                    emailext attachLog: true,
                    compressLog: true,
                    to: 'gayatridevikagapu@gmail.com',
                    body: 'Security Scan Success and log at ${env.BUILD_URL}/consoleText',
                    subject: 'Jenkins Email notification'
                }
                failure {  
                    mail to: "gayatridevikagapu@gmail.com",
                    subject: "Test Jenkins Email",
                    body: "Security Scan Failure"  
                }
            }
        }
        stage('Integration Tests on Staging'){
            steps{
                echo "Integration tests stage.."
                sleep 5
            }
            post{
                success{
                    mail to: "gayatridevikagapu@gmail.com",
                    subject: "Test Jenkins Email",
                    body: "success"
                }
                failure {  
                    mail to: "gayatridevikagapu@gmail.com",
                    subject: "Test Jenkins Email",
                    body: "Failure"  
                } 
            }
             
        }
        stage('Deploy to Production'){
            steps{
                echo "Deploy to Production environment:  $PRODUCTION_ENVIRONMENT "
            }
            
        }
    }
}