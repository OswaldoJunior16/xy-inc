API HTTP para busca de CEP


	O referido projeto consiste em uma API que utiliza o serviço disponibilizado pelos correios, via webservice,  para realização de consultas de endereços. 
	Foi desenvolvido uma tela para demonstração da utilização do serviço, onde consulta pode ser realizada de duas formas: através do CEP ou do logradouro. 
	Os resultados são exibidos em uma tabela com as seguintes informações: logradouro, bairro, cidade e CEP. Que podem ser ordenados através dos cabeçalhos das colunas da tabela. 


Tecnologias utilizadas no desenvolvimento do projeto:

IDE: Eclipse Java EE IDE for Web Developers Neon 4.6.
Linguagem: Java 7.
Servidor de Aplicação: JBoss Wildfly 10.
Front-End: Angular JS, Bootstrap e HTML.
Frameworks de testes: JUnit, Arquillian, Rest-Assured.

Requisitos Funcionais
- Maven versão 3 (ou superior);
- Java 7;
- JBoss WildFly
(Disponível em: http://download.jboss.org/wildfly/10.0.0.Final/wildfly-10.0.0.Final.zip)

Para utilizar a aplicação

1. Obtenção do Projeto:

	O projeto está disponível em https://github.com/OswaldoJunior16/xy-inc.git, pode ser feito um clone do repositório ou download.
	1.1. Em caso de download, descompacte o arquivo do projeto.


2. Inicializar o JBoss Wildfly

	Caso tenha realizado o download do wildfly descompacte a pasta e execute o arquivo "standalone" localizado dentro da pasta bin.
	Linux:   JBOSS_HOME/bin/standalone.sh
	Windows: JBOSS_HOME\bin\standalone.bat



3. Deploy

	Acessar o diretório raiz da api (enderecamentoapp) via Prompt de Comand e executar o comando de deploy: "mvn clean compile wildfly:deploy"


4. Acesso a aplicação

	Acesse a aplicação através de um navegador no seguinte endereço: http://localhost:8080/webapp/
	No desenvolvimento do front-end foi utilizado o Bootstrap que é um framework possui uma diversidade de componentes (plugins) em JavaScript (jQuery) que auxilia bastante na criação de sites com tecnologia mobile (responsivo).
	Com a aplicação front-end é possível:
		- Realizar consulta através do logradouro;
		- Realizar consulta através do CEP;
		- Ordenar os resultados das consultas por logradouro, ou bairro, ou cidade ou CEP.

5. UnDeploy

	Para fazer undeploy da aplicação do servidor, acesse o diretório raiz da api (enderecamentoapp) via Prompt de Comand e execute o comando:  
"mvn wildfly:undeploy"


7. Testes

	Para executar os testes da aplicação, acesse o diretório raiz da api (enderecamentoapp) via Prompt de Comand e execute o comando:
 “mvn clean test -Parq-wildfly-remote”
	No desenvolvimento dos testes foram utilizados os seguintes frameworks:
 
	JUnit é um Framework open-source utilizado para facilitar o desenvolvimento de códigos em Java verificando se os resultados gerados pelos métodos são os esperados. Caso não sejam, o JUnit exibe os possíveis erros que estão ocorrendo nos métodos.
 
	Arquillian é uma plataforma de testes inovadora e altamente extensível para a JVM que permite aos  desenvolvedores criar facilmente integração automatizada, testes funcionais e de aceitação.

	O Rest-Assured é usado para testes de integração da aplicação, ele permite desenvolver os testes para camada REST. Foram implementados alguns testes para cobrir os cenários:
	- Acesso ao serviço de consulta dos correios via webservice;
	- Pesquisa por CEP informando CEP completo;
	- Pesquisa por CEP informando CEP incompleto;
	- Pesquisa por logradouro informando o logradouro completo;
	- Pesquisa por logradouro informando o logradouro incompleto;
	- Pesquisa por CEP inexistente;
	- Pesquisa por logradouro inexistente.
 
