# API - Teste de seleção

### Pré-requisito
Esta API utiliza como banco o MySQL com usuário e senha "dba_onibus", basta criar o usuário no banco com as permissões que a própria API cria o Schema e tabelas.

## Rodando os testes

### 1. API Datapoa

Estas chamadas buscam dados da API http://datapoa.com.br/group/about/mobilidade.

- GET
   
```{host}:{porta}/linhas```

Retorna todas as linhas de ônibus da chamada http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o.

```{host}:{porta}/itinerario/{id}```

Retorna todos os itinerarios da linha informada no id da chamada.

### 2. Banco de dados (MySQL)

As chamadas a seguir realizam operações na base de dados.

- GET

```{host}:{porta}/db/linha```

Retorna todas as linhas cadastradas no banco de dados.

- PUT

```{host}:{porta}/db/linha```

Body:
```
{
    "nome": "NOME_DA_LINHA",
    "codigo": "CÓDIGO_DA_LINHA"
}
```

Este método salva uma nova linha ou altera uma linha existente.
- Caso 1 = Se o nome for igual o nome da linha que existe na base então o código é alterado;
- caso 2 = Se o nome for diferente e o código igual então o nome é alterado.

Obs: O banco tem como chave única o id, nome e codigo do registro, dados que violem esta chave não são gravados.

- GET

```{host}:{porta}/db/itinerario```

Retorna todos os itinerários cadastrados no banco de dados

- PUT

```{host}:{porta}/db/itinerario/{id}```

Body:
```
{
    "lat": "LATITUDE",
    "lng": "LONGITUDE"
}
```

Insere um novo itinerário no banco de dados, o id informado deverá ser o de uma linha previamente cadastrada.

- GET

```{host}:{porta}/db/itinerario/porkm```

Retorna os itinerários encontrados em um raio conforme latitude e longitude informados.
Nesta chamada são necessários 3 parâmetros.

1. km -> contém o raio em km.
2. lat -> contém a latitude do ponto inicial.
3. lng -> contém a longitude do ponto inicial.

## Ferramentas utilizadas
- IDE: SpringTool
- Maven
- Spring Boot
- Validation (Javax)
- Spring Data
- Flyway
- Spring web
- MySQL Driver
