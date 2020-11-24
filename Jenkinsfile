// Feito pelo Kaiquinho o mais vitorioso da cidade
node {
    def app
    def pomModel
    stage('Checkout') {
        checkout scm
    }
    stage('Unit Tests') {
        docker.image('maven:3-alpine') {
            sh 'mvn clean test'
        }
    }
    stage('Build') {      
        pomModel = readMavenPom file: 'pom.xml'
        println("===================== Versão do Maven: " + pomModel.getVersion() + "=====================")
        app = docker.build("kakaique2000/backend-emprego:" + pomModel.getVersion())
    }
    stage('Publish (docker hub)') {
        app.push()
        app.push('latest')
        println("===================== Imagem enviada ao dockerhub: https://hub.docker.com/repository/docker/kakaique2000/backend-emprego =====================")
    }
    stage('Publish (production)') {      
         try {
            sh 'docker stop backend-emprego'
            sh 'docker rm backend-emprego'
        } catch(Exception ex) {
         println("nao foi possivel parar o container backend-emprego: " + ex)
        }
        sh 'docker image prune -f'
        
        println("===================== Executando imagem: kakaique2000/backend-emprego:" + pomModel.getVersion()  + "=====================")
        sh 'docker run -d -p 8080:8080 --name backend-emprego kakaique2000/backend-emprego:latest'
    }
}
