# SBSeg2021
Repositório com os documentos e códigos fontes submetidos ao Salão de Ferramentas do SBSeg 2021.

## 📌 Overview
 #### 1. **<a href="https://github.com/anonymousgithub21/SBSeg2021/tree/master/Aplicativo"> 📱 Aplicativo </a>**

Nesta pasta, encontram-se todos os códigos-fontes necessários para rodar a aplicação móvel em um em um emulador ou _smartphone_ com sistema operacional Android.

#### 2.  **<a href="https://github.com/anonymousgithub21/SBSeg2021/tree/master/Gestor%20Automatizado"> 👷 Gestor Automatizado </a>**

Nesta pasta, encontram-se todos os códigos-fontes necessários para rodar o algoritmo do Gestor Automatizado.

#### 3. **<a href="https://github.com/anonymousgithub21/SBSeg2021/tree/master/BKE_Auth4ISP_Scyther"> ✅ Verificação de Protocolo de Segurança com Scyther </a>**

Na pasta BKE_Auth4ISP_Scyther encontra-se o código .spdl utilizado para verificar a segurança do protocolo BKE4ISP, além de instruções sobre como executá-lo.


## 📝Manual de Instalação e Instruções para Testes no Aplicativo e no Gestor Automatizado.

## **🏷️ Nota** 
Nesta implementação, utilizamos o Java SE Development Kit 8 para executar arquivos java, a IDE Apache Netbeans em sua versão 12.3 para implantar o Gestor Automatizado, o ambiente de desenvolvimento integrado Android Studio, na sua versão 4.2 para desenvolver para a plataforma Android, além dos  injetores de dependências Maven (para o Netbeans) e Gradle (para o Android Studio). O desenvolvimento foi realizado em uma máquina com o Sistema Operacional de 64 bits - Windows 10 Home. Os dados são persistidos no Firebase Realtime Database.

## **📝 Requisitos** 
Nós disponibilizamos três formas de testes. Na primeira e segunda, os requisitos necessários consistem no Java SE Devolopment Kit para rodar um arquivo do tipo .jar e um _smartphone_ com o sistema operacional Android para rodar um arquivo com a extensão .apk. Na terceira forma, é necessário os mesmos requisitos da 1º e 2º forma, além das IDEs e os injetores de dependência citados na nota acima. Observação: O _smartphone_ deve estar configurado para permitir a instalação de aplicativos desconhecidos. Para saber como ativar esse tipo de instação, **<a href="https://www.showmetech.com.br/instalando-aplicativos-android-de-fontes-desconhecidas/">clique aqui</a>**.

## **⚙️ Instalação**
#### **⚠️ Importante**: Para que todos os processos funcionem corretamente, é necessário que o **Gestor Automatizado** esteja **em execução**.

## **1️⃣ Forma** 
Nós disponibilizamos o Gestor Automatizado em um servidor público, dispensando a execução manual. Assim, basta instalar o aplicativo em seu smartphone utilizando o arquivo <a href="https://github.com/anonymousgithub21/SBSeg2021/blob/master/identificaispremoto.apk">identificaispremoto.apk</a>. Na tela de login, basta entrar com um dos usuários pré-cadastrados:
|              |  Cliente       |    Técnico       | Gestor Humano   |
| :---:        |     :---:      |         :---:    |  :---:   |
| Usuário 1:   | 408.345.420-21 | 514.775.490-30   |  946.234.100-13 |
| Password 1:  | 40834542021    | 51477549030      |  94623410013    |
| Usuário 2:   | 716.421.960-53 | 583.753.510-16   |  - |
| Password 2:  | 71642196053    | 58375351016      |  - |


## 2️⃣ **Forma** 
A segunda forma consiste em realizar o download do arquivo que representa o Gestor Automatizado (<a href="https://github.com/anonymousgithub21/SBSeg2021/blob/master/gestorautomatizado.jar">gestorautomatizado.jar</a>) e do aplicativo Identifica ISP (<a href="https://github.com/anonymousgithub21/SBSeg2021/blob/master/identificaisplocal.apk">identificaisplocal.apk</a>) para executá-los localmente. O Gestor Automatizado deve ser executado em uma máquina (i.e., computador) e deve conter o arquivo (<a href="https://github.com/anonymousgithub21/SBSeg2021/blob/master/inovaisp-firebase-adminsdk-urcvf-1b5492eea5.json">inovaisp-firebase-adminsdk-urcvf-1b5492eea5.json</a>) no mesmo diretório. O aplicativo deve ser executado em um smartphone. Os arquivos estão disponíveis neste repositório. Para testar o fluxo de autenticação, o gestor automatizado deve estar em execução. Na tela de login, é necessário entrar com o seguinte usuário pré-cadastrado:
|              |  Cliente       |    Técnico       | Gestor Humano   |
| :---:        |     :---:      |         :---:    |  :---:   |
| Usuário 1:   | 955.860.260-40 | 588.407.470-01   |  946.234.100-13 |
| Password 1:  | 95586026040    | 58840747001      |  94623410013    |


## 3️⃣ **Forma**
A terceira forma abrange o download e a execução manual dos códigos-fontes do **<a href="https://github.com/anonymousgithub21/SBSeg2021/tree/master/Aplicativo">Aplicativo </a>** e do **<a href="https://github.com/anonymousgithub21/SBSeg2021/tree/master/Gestor%20Automatizado">Gestor Automatizado</a>**. Tais aplicações estão identificadas no repositório conforme o nome destacado em negrito. Após a execução de ambos, os testes podem ser feitos utilizando os mesmos usuários da 2ª Forma.

🏷️ Nota: Utilizamos os IDEs Apache Netbeans em sua versão 12.3 (para o Gestor Automatizado) e Android Studio na versão 4.2 (para o Aplicativo), junto com os injetores de dependência Maven e Gradle. Também, utilizamos o Java SE Development Kit na versão 8 em um Windows 10 com base em 64 bits.

## ✔️ Já instalei! Como Testar?!

Basicamente,  o aplicativo Identifica ISP tem três funcionalidades principais: _(i)_ autenticar clientes e técnicos, _(ii)_ simular a integração com uma API externa, _(iii)_ avaliação de técnicos.

➡️ _(i)_ Para testar o processo de autenticação, o **Técnico**, na tela inicial, deve clicar em _**gerar código de autenticação**_. O **Cliente**, por sua vez, deve clicar em _**ler código de autenticação**_ e apontar a câmera para o QR Code do **técnico**. Após a autenticação, ambas identidades são exibidas. 

➡️ **O Gestor Humano** pode revogar a identificação de um cliente ou técnico, clicando no botão _**revogar identificação**_. Assim, o mesmo não poderá mais ser autenticado.

➡️ _(ii)_ Para simular uma **API Externa**, o Gestor Humano deve clicar em _**gerenciar usuários**_ e no botão representado por um "_**+**_". Para simular adição de clientes, os CPFs podem ser utilizados:  **24268060014** ou **94547931011**. Para técnico, o CPF **04186570094**.

➡️ _(iii)_ Para simular a avaliação de um técnico, o Cliente deve ter um chamado registrado. Depois, é só clicar no **_chamado_**, preencher com uma ou cinco estrelas, e opcionalmente, escrever um comentário. Depois, é só relatar o feedback.

**Observação.** Os clientes com CPFs 40834542021 e 95586026040 possuem chamados registrados.
