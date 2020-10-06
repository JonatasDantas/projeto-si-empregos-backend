// Feito pelo Kaiquinho o mais vitorioso da cidade
node {
    def app
    stage('Checkout') {
        checkout scm
    }
    stage('Build') {
      
        def version = $(sh "cat pom.xml | grep \"<version>.*</version>\" | head -1 |awk -F'[><]' '{print $3}'")

        app = docker.build("kakaique2000/backend-emprego:" + version)
    }
    stage('Publish (docker hub)') {
        app.push()
        app.push('latest')
    }
    stage('Publish (production)') {
      
         try {
            sh 'docker-compose stop backend-emprego'
            sh 'docker rm backend-emprego'
        } catch(Exception ex) {
         println("nao foi possivel parar o container backend-emprego: " + ex)
        }
        sh 'docker image prune -f'
        sh 'docker-compose up backend-emprego'
    }
}
