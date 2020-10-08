// Feito pelo Kaiquinho o mais vitorioso da cidade
node {
    def app
    stage('Checkout') {
        checkout scm
    }
    stage('Build') {
      
        def pomModel = readMavenPom file: 'POM.xml'
        printLn(pomModel.getModelVersion())
        printLn(pomModel)
        app = docker.build("kakaique2000/backend-emprego:" + pomModel.getModelVersion())
    }
    stage('Publish (docker hub)') {
        app.push()
        app.push('latest')
    }
    stage('Publish (production)') {      
         try {
            sh 'docker stop backend-emprego'
            sh 'docker rm backend-emprego'
        } catch(Exception ex) {
         println("nao foi possivel parar o container backend-emprego: " + ex)
        }
        sh 'docker image prune -f'
        sh 'docker run -d -p 8080:8080 --name backend-emprego kakaique2000/backend-emprego:latest'
    }
}
