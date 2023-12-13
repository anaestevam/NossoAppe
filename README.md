# NossoAppê - Gerenciador de despesas compartilhadas

Este é um aplicativo de controle e gerenciamento de despesas compartilhadas, desenvolvido como parte da avaliação da disciplina de Programação de Dispositivos Móveis I na Universidade Federal do Rio Grande do Norte.

## Descrição do Projeto

O projeto é estruturado em três principais diretórios:      `adapters`, `controller`, `model` e `database`.

### Diretório `adapters`

O diretório adapters contém as classes que auxiliam a exibição de Gastos e Moradores.

### Diretório `controller`

A pasta `controller` do projeto contém as classes que gerenciam a lógica de interação entre a interface do usuário e os dados.

Uma breve descrição das classes encontradas na pasta:

1. `CadastroGActivity.java` e `CadastroMActivity.java`: Essas classes lidam com as atividades de registro para `Gasto` e `Morador`, respectivamente. Elas contém lógicas para validar entradas de usuário e salvar novos `Gasto` e `Morador` no banco de dados.

2. `EditarGastoActivity.java` e `EditarMoradorActivity.java`: Essas classes lidam com as atividades de edição para `Gasto` e `Morador`.

3. `ListaMActivity.java`: Essa classes lida com as atividades de listagem da tabela `Morador`.

4. `ListadeGastos.java`: Esta classe lida com a atividade de exibir uma lista de `Gasto`.

5. `LoginActivity.java`: Esta classe lida com a atividade de login do usuário.

6. `MainActivity.java`: Esta é a classe de entrada do aplicativo. Ela é a primeira atividade a ser lançada quando o aplicativo é iniciado.

7. `MenuActivity.java`: Esta classe lida com a atividade do menu principal do aplicativo.

8. `MudarSenhaActivity.java`: Esta classe lida com a atividade de mudança de senha do usuário. 

Cada uma dessas classes é uma `Activity`, que é uma componente fundamental de qualquer aplicativo Android. Uma `Activity` representa uma única coisa que o usuário pode fazer, é basicamente uma janela na interface do usuário.

### Diretório `model`

O diretório model contém as classes que representam os objetos de negócio, tais como `Gasto` e `Morador`, além de do `BancoDAO` que é usado para interagir com o banco de dados do aplicativo, onde herda de SQLiteOpenHelper, uma classe auxiliar para gerenciar a criação de banco de dados.

    salvarMorador(Morador morador): Este método é usado para inserir um novo objeto Morador no banco de dados.

    atualizarMorador(Morador morador): Este método é usado para atualizar um objeto Morador existente no banco de dados.

    moradorExiste(String nome): Este método verifica se um morador com o nome fornecido já existe no banco de dados.

    deletarMorador(String nomeMorador): Este método é usado para deletar um Morador do banco de dados pelo nome.

    getListaMoradores(): Este método recupera todos os moradores do banco de dados e retorna uma lista de objetos Morador.

    getIdMoradorPorNome(String nome): Este método recupera um morador pelo nome e retorna seu ID.

    salvarGasto(Gasto gasto): Este método é usado para inserir um novo objeto Gasto no banco de dados.

    atualizarGasto(Gasto gasto): Este método é usado para atualizar um objeto Gasto existente no banco de dados.

    gastoExiste(String nome): Este método verifica se um gasto com o nome fornecido já existe no banco de dados.

    deletarGasto(String nomeGasto): Este método é usado para deletar um Gasto do banco de dados pelo nome.

    getListaGastos(): Este método recupera todos os gastos do banco de dados e retorna uma lista de objetos Gasto.

    atualizarPagamentoGasto(Gasto gasto): Este método é usado para atualizar o status de pagamento de um Gasto existente no banco de dados.


### Diretório `database`

O diretório database contém as classes responsáveis para o BancoDAO realizar operações ou consultas nas tabelas.

A classe Produto representa o produto e que possui os métodos get e set, e a classe.

 O arquivo é lido ao iniciar o aplicativo para carregar os dados já salvos e é salvo ao fechar o aplicativo após qualquer manipulação de dados.

## Como Instalar e Executar o Projeto

Para instalar e executar este projeto, siga as seguintes etapas:

1. Clone o repositório para o seu computador usando o comando `git clone https://github.com/anaestevam/NossoAppe.git`.
2. Abra o projeto no seu ambiente de desenvolvimento, no caso o Android Studio.
3. Execute o aplicativo no seu dispositivo ou emulador.

**Versões android utilizada:**<br>
*"Lollipop", android 5.1*

## Como Usar o Projeto

O aplicativo permite a criação, listagem, detalhamento, alteração e exclusão de moradores e gastos. Para usar o aplicativo, siga as seguintes etapas:

1. Abra o aplicativo no seu dispositivo ou emulador.
2. Faça login usando suas credenciais "admin" "admin" ou mude a senha.
3. Selecione a funcionalidade desejada (criação, listagem, detalhamento, alteração e exclusão de moradores e gastos) na tela de menu.
4. Siga as instruções na tela para realizar a funcionalidade desejada.
5. Após entrar no Menu e selecionar um botão para outra tela, pode-se clicar no layout NossoAppê que volta para a tela Menu.


## Autores

Ana Luisa Estevam Dantas e Debora Tayna da Silva Chacon