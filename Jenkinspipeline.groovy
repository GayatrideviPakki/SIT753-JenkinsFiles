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
        stage('Test'){
            steps{
                echo "unit tests running.. "
                echo "integration tests running .. "
            }
        }
        stage('Code Quality Check'){
            steps{
                echo "check the quality of the code"
            }
        }
        stage('Deploy'){
            steps{
                echo "deploy the application to testing environment: $TESTING_ENVIRONMENT"
            }
        }
        stage('Approval'){
            steps{
                echo "Approval stage.."
                sleep 10
            }
        }
        stage('Deploy to Production'){
            steps{
                echo "Deploy to Production environment:  $PRODUCTION_ENVIRONMENT "
            }
        }
    }
}