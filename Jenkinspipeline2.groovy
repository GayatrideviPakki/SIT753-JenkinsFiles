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
                echo "fetching the source code from directory: $DIRECTORY_PATH "
                echo "compile the code and generate any necessary artifacts ......"
            }
        }
        stage('Unit and Integration Tests'){
            steps{
                echo "unit tests running.. "
                echo "integration tests running ....! "
            }
        }
        stage('Code Analysis'){
            steps{
                echo "Analyse Code"
            }
        }
        stage('Security Scan'){
            steps{
                echo "deploy the application to testing environment: $TESTING_ENVIRONMENT"
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
        stage('Integration Tests on Staging'){
            steps{
                echo "Approval stage.."
                sleep 10
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