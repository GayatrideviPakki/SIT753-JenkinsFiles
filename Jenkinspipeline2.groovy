pipeline{
    agent any
    environment {
        DIRECTORY_PATH="C:/Program Files/Jenkins"
        TESTING_ENVIRONMENT="CCS_ICT_UAT"
        PRODUCTION_ENVIRONMENT="CCS_ICT_PROD"
    }
    stages{
        stage('Build'){
            steps{
                echo "fetching the source code from the directory: $DIRECTORY_PATH "
                echo "compile the code and Build the code using [Maven] to compile and package code. ......"
            }
        }
        stage('Unit and Integration Tests'){
            steps{
                echo "unit test is running in this stage from [Buildkite].. "
                echo "integration tests are running from [Buildkite]....! "
                echo "Unit and Integration Tests completed ...."
            }
        }
        stage('Code Analysis'){
            steps{
                echo "Static Code quality analysis being performed using [SonarQube]...."
            }
        }
        stage('Security Scan'){
            steps{
                echo "Security Scan started by [SonarQube] ..."
            }
            post{
                success{
                    emailext to: 'gayatridevikagapu@gmail.com',
                    body: 'Security Scan Successful. Please find more details in the log file',
                    subject: 'Security Scan Success - Jenkins'
                }
                failure {  
                    emailext to: "gayatridevikagapu@gmail.com",
                    subject: "Security Scan Failure - Jenkins",
                    body: "Security Scan Failure"  
                }
            }
        }
        stage('Deploy to Staging'){
            steps{
                echo "Deploying code into Test environment: $TESTING_ENVIRONMENT"
            }
        }
        stage('Integration Tests on Staging'){
            steps{
                echo "Running Integration tests at this stage.."
                sleep 5
            }
            post{
                success{
                    emailext to: "gayatridevikagapu@gmail.com",
                    subject: "Testing in staging is Success - Jenkins",
                    body: "Testing in staging is Success"
                }
                failure {  
                    emailext to: "gayatridevikagapu@gmail.com",
                    subject: "Testing in staging failed - Jenkins",
                    body: "Testing in staging failed, please check the logs "  
                } 
            }
             
        }
        stage('Deploy to Production'){
            steps{
                echo "Deploy to Production environment:  $PRODUCTION_ENVIRONMENT "
            }
             post{
                success{
                    emailext attachLog: true,
                    compressLog: true,
                    to: 'gayatridevikagapu@gmail.com',
                    body: 'Deploy to Production environment is finished and build log is available at $JENKINS_HOME/jobs/$JOB_NAME/builds/lastSuccessfulBuild/log',
                    subject: 'Deployment to Production Failed is Success - Jenkins'
                }
                failure {  
                    emailext attachLog: true,
                    compressLog: true,
                    to: 'gayatridevikagapu@gmail.com',
                    body: 'Deploy to Production environment is finished and build log is available at $JENKINS_HOME/jobs/$JOB_NAME/builds/lastSuccessfulBuild/log',
                    subject: "Deployment to Production Failed - Jenkins"  
                }
            }
        }
    }
}