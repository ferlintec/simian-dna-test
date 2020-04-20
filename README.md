<img src="https://i.ibb.co/ysH99tH/1-dna.jpg" width="20%" heigth="20%" data-load="full" style border="0">

# Teste Símios
---

Em um futuro distante, na cadeia de evolução, os símios e os humanos estão cada vez mais próximos. Por esse motivo ficou muito difícil distinguir quem é humano e quem é símio.

O DNA é composto por uma sequência de bases, representadas, cada base, pelas letras **ACTG**.

O DNA será representado por uma matriz quadrada. 
O DNA de um símio pode ser detectado pela repetição consecutiva de, pelo menos, 4 vezes a mesma base, tanto em linha, coluna ou na vertical. Por exemplo:

```
CTGAA CTTTT AGGTC GTCAGT CTCTG
```

Neste exemplo, o DNA pertence a um *Simian*, pois contém a sequência **TTTT** na segunda linha do DNA.

Caso a DNA não contenha a repetição de 4 bases, como informado acima, o DNA é humano.


---
## Solução

Como solução, o sistema disponibiliza duas APIs REST: **/simian** e **/stats**.

Elas serão detalhadas mais abaixo.

O método **isSimian(String[] dna)** está implementado na classe ```br.com.ferlintec.dna.SimianDNAVerfication```.




---
## Configuração de ambiente

Requisitos:

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) ou superior
- [GIT](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Maven 3.6.3](http://maven.apache.org/download.cgi)
- [MySql 5.7.22](https://dev.mysql.com/doc/mysql-apt-repo-quick-guide/en/#apt-repo-fresh-install)
- [Postman](https://www.postman.com/downloads/)
- Sistema operacional Linux, Windows ou Mac


### Instalação do Java

- talar o [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) , conforme as isntruções para seu sistema operacional.


### Instalação do Maven

- stalar o [Maven 3.6.3](https://maven.apache.org/install.html) , conforme as isntruções para seu sistema operacional.


### Instalar e Configurar banco de dados MySQL

- Instalar o [MySQL 5.7.22](https://dev.mysql.com/doc/mysql-apt-repo-quick-guide/en/#apt-repo-fresh-install), ou superior.
- Configurar o banco na porta 3306
- Criar username *root* e senha *admin123*.
- Criar database schemma com nome 'simian'.


### Instalação da IDE (Eclipse ou IntelliJ IDEA)

Escolher uma das opções. Não há restrições quanto a outra IDE.

- **Opção 1:** Instalar [Eclipse](https://www.eclipse.org/downloads/packages/release/2020-03/r/eclipse-ide-enterprise-java-developers-includes-incubating-components), conforme as isntruções para seu sistema operacional.
- **Opção 2:** Instalar [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows), conforme as isntruções para seu sistema operacional.


### Clonar o projeto

- Criar um diretório para armazenar o projeto.
- Abrir o GitBash neste diretório.
- Executar o comando 'git clone https://github.com/ferlintec/simian-dna-test.git'

Pronto. Os arquivos do projeto estarão salvos no diretório.

---
## Inicialização do sistema

Para inicialização adequada, é necessário que o MySQL esteja rodando, e com o database 'simian' já criado.


### Inicializar sistema pela IDE

- Abrir IDE de sua escolha (Eclipse ou Intllij IDEA).
- Importar projeto como *Projeto Maven existente*. O projeto está dentro da pasta *<diretório raiz clonado>/SimianDNAVerification/SimianDNAVerification**.
- Após importação do projeto, verificar se o mesmo está configurado com Java 11.
- Acesse a classe ```br.com.ferlintec.Statup.java```, e execute como **Java Application**.


### Inicializar sistema via linha de comando

- Abrir terminal do sistema operacional.
- Acessar o diretório do projeto: *<diretório raiz clonado>/SimianDNAVerification/SimianDNAVerification**.
- Executar o comando:
```mvn clean package spring-boot:run```

---
## Orientação para Consumo das APIs

- Recomendado utilizar Postman para realização das chamadas.

### API REST '/simian'

Esta chamada verifica se o DNA pertence a um simian ou humano.

Executar chamada do tipo **HTTP POST**. 
Informar no *body* um **JSON** com a sequência de DNA a ser testada, conforme o exemplo:

```
{
"dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
```

Exemplo de chamada:

```
http://localhost:8080/simian
```

Exemplo de retorno:

```
true
```

A cada chamada, o sistema armazena o DNA na base de dados. 
Um mesmo DNA válido não será inserido uma única vez, ou seja, não haverá duplicidade na base de dados.


**Retorno**

- Caso o DNA seja Simian, retorna **true** com **HTTP STATUS 200 - OK**.
- Caso o DNA seja Humanon, retorna **false** com **HTTP STATUS 403 - FORBIDDEN**.
- Caso o DNA contenha letra inválida, diferente de **ACTG**, gera exceção com **HTTP STATUS 406 - NOT_ACCEPTABLE**.
- Caso o DNA não forme uma **matriz quadrada**, gera exceção com **HTTP STATUS 406 - NOT_ACCEPTABLE**.
- Caso ocorra outro erro, gera exceção com **HTTP STATUS 500 - INTERNAL_SERVER_ERROR**.


### API REST '/stats'

Este serviço verifica a estatísca de DNAs testados, e a proporção de DNAs símios sobre o total de DNAs humanos.

Chamada do tipo **HTTP GET**. Não possui parâmetro de entrada.

Exemplo de chamada:

```
http://localhost:8080/stats
```

**Retorno**

Retorna um **JSON** com o total de DNAs mutantes símios (*count_mutant_dna*), DNAs humanos (*count_human_dna*) e a proporção entre eles (*ratio*).

```
{
  "count_mutant_dna": 40, 
  "count_human_dna": 100: 
  "ratio": 0.4
}

```

---
## Acesso via Cloud AWS

As APIs estão disponibilizadas na AWS, nos seguintes endereços:

- <host_da_aws>:8080/simian
- <host_da_aws>:8080/stats

**IMPORTANTE: O sistema ainda não está liberado para a AWS, pois o cadastro da conta foi realizado dia 19/04/2020, e demora 24 horas para liberação dos serviços.**

---
## Contato

- Adriano Ferlin
- adriano.ferlin@gmail.com
- +55 (48) 99941-2220
