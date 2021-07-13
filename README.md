# SBSeg2021
Repositório com os documentos e códigos fontes submetidos ao Salão de Ferramentas do SBSeg 2021.

-> Na pasta Aplicativo, encontram-se todos os códigos-fontes necessários para rodar a aplicação móvel em um emulador ou smartphone com sistema operacional Android.
-> Na pasta Gestor Automatizado, encontram-se todos os códigos-fontes necessários para rodar o gestor automatizado. 

Em resumo, existem três formas para realizar testes:

1° - Executando o arquivo do Aplicativo (.apk) e utilizando o Gestor Automatizado disponível nos nossos servidores.
1.1 - O Gestor Automatizado está sendo executado em um servidor publico nosso. Assim, não é preciso executá-lo.
2.2 - Basta instalar o aplicativo (.apk) em seu smartphone e realizar os testes. 

2° - Executando os arquivos .jar do Gestor Automatizado e .apk do Aplicativo
2.1 - Baixe e execute o arquivo do Gestor Automatizado (.jar) na sua máquina (e.g., servidor, notebook, etc).
2.2 - Baixe e execute o arquivo do Aplicativo (.apk) no seu smartphone/emulador. 
2.3 - Realize os testes.
Obs: O Gestor Automatizado deve estar em execução para realizar testes nas funcionalidades de autenticação.

3° - Verificando códigos-fontes e executando a partir de IDEs
3.1 - Você deve realizar um git pull da pasta Aplicativo na sua IDE de preferência (e.g., Android Studio)
3.2 - Inicialize um emulador ou smartphone
3.3 - Clique em "Run app". Assim, o aplicativo será instalado.
Agora, para que obtenha êxito no processo de autenticação, você deve executar o Gestor Automatizado:
3.4 - Realize um git pull da pasta Gestor Automatizado na sua IDE de preferência (e.g., Netbeans, IntelliJ)
3.5 - Clique em Run Main Project, ou simplesmente tecle (F6)
3.6 - A partir do momento em que o Gestor Automatizado estiver em execução, os testes com as funcionalidades de autenticação podem ser testadas.
