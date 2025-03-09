pipeline 
{
    agent any
    
    tools
    {
		maven 'Maven3'
	}
    
    stages
    {
        stage("Build")
        {
			steps {
                sh '/path/to/maven/bin/mvn clean install'
            }
            
        }

        stage("Deploy to QA")
        {
            steps
            {
                echo("running the test automation regression scripts")
                catchError(buildResult: 'SUCCESS' , stageResult: 'FAILURE' )
                {
                git 'https://github.com/Vijeymugundhan/OpenCart_Hybrid-Framework'
                sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/Regression_Test.xml"
                }
            }
            
        }
        stage("Publish Allure Reports")
        {
            steps
            {
				script
				{
					allure([
						includeProperties: false,
						jdk: '',
						properties: [],
						reportBuildPolicy: 'ALWAYS',
						results:[[path: '/allure-results']]
					])
					
				}
          
            }
            
        }
        
         stage("Publish Extent Reports")
        {
            steps
            {				
				publishHTML([allowMissing: false,
				alwaysLinkToLastBuild: false,
				keepAll: true,
				reportDir: 'reports',
				reportFiles: 'TestExecutionReport.html',
				reportName: 'HTML Extent Report',
				reportTitles: ''])
            }
            
        }
        
        
        
        
        
         stage("Deploy to Stage")
        {
            steps
            {
                echo("Stage Deployment")
            }
            
        }
        stage("Run Sanity Automation Test")
        {
            steps
            {
                 echo("running the test automation sanity scripts")
                catchError(buildResult: 'SUCCESS' , stageResult: 'FAILURE' )
                {
                git 'https://github.com/Vijeymugundhan/OpenCart_Hybrid-Framework'
                sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/Smoke_Test.xml"
                }
            }
            
        }
        
        stage("Publish sanity Extent Reports")
        {
            steps
            {				
				publishHTML([allowMissing: false,
				alwaysLinkToLastBuild: false,
				keepAll: true,
				reportDir: 'reports',
				reportFiles: 'TestExecutionReport.html',
				reportName: 'HTML Sanity Extent Report',
				reportTitles: ''])
            }
            
        }
        
        
        stage("Deploy to Prod")
        {
            steps
            {
                echo("prod Deployment")
            }
            
        }
        
       
    }
}