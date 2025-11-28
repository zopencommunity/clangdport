node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/clangdport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/clangdport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'clangd - the clang language server'),
      string(name: 'BUILD_LINE', value: 'STABLE')
    ]
  }
}
