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



> API REST '/simian'

Esta chamada verifica se o DNA pertence a um simian ou humano.

Chamada do tipo **HTTP POST**. 
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

A cada chamada, o sistema armazena o DNA na base de dados. 
Um mesmo DNA válido não será inserido uma única vez, ou seja, não haverá duplicidade na base de dados.


**Retorno**

- Caso o DNA seja Simian, retorna **true** com **HTTP STATUS 200 - OK**.
- Caso o DNA seja Humanon, retorna **false** com **HTTP STATUS 403 - FORBIDDEN**.
- Caso o DNA contenha letra inválida, diferente de **ACTG**, gera exceção com **HTTP STATUS 406 - NOT_ACCEPTABLE**.
- Caso o DNA não forme uma **matriz quadrada**, gera exceção com **HTTP STATUS 406 - NOT_ACCEPTABLE**.
- Caso ocorra outro erro, gera exceção com **HTTP STATUS 500 - INTERNAL_SERVER_ERROR**.


> API REST '/stats'

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
## Configuração de ambiente

Requisitos:

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) ou superior
- [GIT](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Maven 3.6.3](http://maven.apache.org/download.cgi)
- [MySql 5.7.22](https://dev.mysql.com/doc/mysql-apt-repo-quick-guide/en/#apt-repo-fresh-install)
- [Postman](https://www.postman.com/downloads/)
- Sistema operacional Linux, Windows ou Mac


> Instalação do Java

Instalar o [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) , conforme as isntruções para seu sistema operacional.


**Instalação do Maven**

Instalar o [Maven 3.6.3](https://maven.apache.org/install.html) , conforme as isntruções para seu sistema operacional.


**Instalar e Configurar banco de dados MySQL**

1 - Instalar o [MySQL 5.7.22](https://dev.mysql.com/doc/mysql-apt-repo-quick-guide/en/#apt-repo-fresh-install), ou superior.
2 - Configurar o banco na porta 3306
3 - Criar username *root* e senha *admin123*.
4 - Criar database schemma com nome 'simian'.


**Instalação da IDE (Eclipse ou IntelliJ IDEA)

Escolher uma das opções. Não há restrições quanto a outra IDE.

**Opção 1:** Instalar [Eclipse](https://www.eclipse.org/downloads/packages/release/2020-03/r/eclipse-ide-enterprise-java-developers-includes-incubating-components), conforme as isntruções para seu sistema operacional.

**Opção 2:** Instalar [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows), conforme as isntruções para seu sistema operacional.



**Clonar o projeto**

1 - Criar um diretório para armazenar o projeto.
2 - Abrir o GitBash neste diretório.
3 - Executar o comando 'git clone https://github.com/ferlintec/simian-dna-test.git'

Pronto. Os arquivos do projeto estarão salvos no diretório.

---
## Inicialização do sistema

Para inicialização adequada, é necessário que o MySQL esteja rodando, e com o database 'simian' já criado.


**Inicializar sistema pela IDE**

1 - Abrir IDE de sua escolha (Eclipse ou Intllij IDEA).
2 - Importar projeto como *Projeto Maven existente*. O projeto está dentro da pasta *<diretório raiz clonado>/SimianDNAVerification/SimianDNAVerification**.
3 - Após importação do projeto, verificar se o mesmo está configurado com Java 11.
4 - Acesse a classe ```br.com.ferlintec.Statup.java```, e execute como **Java Application**.


**Inicializar sistema via linha de comando**

1 - Abrir terminal do sistema operacional.
2 - Acessar o diretório do projeto: *<diretório raiz clonado>/SimianDNAVerification/SimianDNAVerification**.
3 - Executar o comando:
```mvn clean package spring-boot:run```



> Subtitle or Short Description Goes Here

> ideally one sentence

> include terms/tags that can be searched

**Badges will go here**

- build status
- issues (waffle.io maybe)
- devDependencies
- npm package
- coverage
- slack
- downloads
- gitter chat
- license
- etc.

- Most people will glance at your `README`, *maybe* star it, and leave
- Ergo, people should understand instantly what your project is about based on your repo

> Tips

- HAVE WHITE SPACE
- MAKE IT PRETTY
- GIFS ARE REALLY COOL

> GIF Tools

- Use <a href="http://recordit.co/" target="_blank">**Recordit**</a> to create quicks screencasts of your desktop and export them as `GIF`s.
- For terminal sessions, there's <a href="https://github.com/chjj/ttystudio" target="_blank">**ttystudio**</a> which also supports exporting `GIF`s.

**Recordit**

![Recordit GIF](http://g.recordit.co/iLN6A0vSD8.gif)

---

## Table of Contents (Optional)

> If your `README` has a lot of info, section headers might be nice.

- [Installation](#installation)
- [Features](#features)
- [Contributing](#contributing)
- [Team](#team)
- [FAQ](#faq)
- [Support](#support)
- [License](#license)


---

## Example (Optional)

```javascript
// code away!

let generateProject = project => {
  let code = [];
  for (let js = 0; js < project.length; js++) {
    code.push(js);
  }
};
```

---

## Installation

- All the `code` required to get started
- Images of what it should look like

### Clone

- Clone this repo to your local machine using `https://github.com/fvcproductions/SOMEREPO`

### Setup

- If you want more syntax highlighting, format your code like this:

> update and install this package first

```shell
$ brew update
$ brew install fvcproductions
```

> now install npm and bower packages

```shell
$ npm install
$ bower install
```

- For all the possible languages that support syntax highlithing on GitHub (which is basically all of them), refer <a href="https://github.com/github/linguist/blob/master/lib/linguist/languages.yml" target="_blank">here</a>.

---

## Features
## Usage (Optional)
## Documentation (Optional)
## Tests (Optional)

- Going into more detail on code and technologies used
- I utilized this nifty <a href="https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet" target="_blank">Markdown Cheatsheet</a> for this sample `README`.

---

## Contributing

> To get started...

### Step 1

- **Option 1**
    - 🍴 Fork this repo!

- **Option 2**
    - 👯 Clone this repo to your local machine using `https://github.com/joanaz/HireDot2.git`

### Step 2

- **HACK AWAY!** 🔨🔨🔨

### Step 3

- 🔃 Create a new pull request using <a href="https://github.com/joanaz/HireDot2/compare/" target="_blank">`https://github.com/joanaz/HireDot2/compare/`</a>.

---

## FAQ

- **How do I do *specifically* so and so?**
    - No problem! Just do this.

---

## Support

Reach out to me at one of the following places!

- Website at <a href="http://fvcproductions.com" target="_blank">`fvcproductions.com`</a>
- Twitter at <a href="http://twitter.com/fvcproductions" target="_blank">`@fvcproductions`</a>
- Insert more social links here.

---

## Donations (Optional)

- You could include a <a href="https://cdn.rawgit.com/gratipay/gratipay-badge/2.3.0/dist/gratipay.png" target="_blank">Gratipay</a> link as well.

[![Support via Gratipay](https://cdn.rawgit.com/gratipay/gratipay-badge/2.3.0/dist/gratipay.png)](https://gratipay.com/fvcproductions/)


---

## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- **[MIT license](http://opensource.org/licenses/mit-license.php)**
- Copyright 2015 © <a href="http://fvcproductions.com" target="_blank">FVCproductions</a>.
