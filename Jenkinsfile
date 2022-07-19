pipeline {
  agent {
      label 'scanner'
  }
  stages {
    stage('Scan') {
      steps {
        sh "docker run -v ${WORKSPACE}:/src --workdir /src returntocorp/semgrep-agent semgrep-agent --config p/ci --config p/security-audit --config p/secrets"
      }
    }
  }
}
