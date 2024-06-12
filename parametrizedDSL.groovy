job('example2-job-DSL') {
	description('Job Dsl Example for jenkins job')
  	 scm {
        git('https://github.com/UserMl9/jenkins.job.parametrized.git', 'main') { node ->
            node / gitConfigName('UserMl9')
            node / gitConfigEmail('aprendizajeml9@gmail.com')
        }
    }
  	parameters {
      	stringParam('name', defaultValue = 'User', description = 'String parameter for boolean Job')
        choiceParam('planet', ['Mercury', 'Venus', 'Earth','Mars', 'Jupiter', 'Saturn', 'Uranus', 'Neptune'])
      	booleanParam('agent', false)
    }
  	triggers {
		cron('H/7 * * * *')
	}
  	steps {
		shell("bash jobscript.sh")
	}
  	publishers {
		mailer('aprendizajeml9@gmail.com', true, true)
      	slackNotifier {
          notifyAborted(true)
          notifyEveryFailure(true)
          notifyNotBuilt(false)
          notifyUnstable(false)
          notifyBackToNormal(true)
          notifySuccess(false)
          notifyRepeatedFailure(false)
          startNotification(false)
          includeTestSummary(false)
          includeCustomMessage(false)
          customMessage(null)
          sendAs(null)
          commitInfoChoice('NONE')
          teamDomain(null)
          authToken(null)
        }
	}
}
