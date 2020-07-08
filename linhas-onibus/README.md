# API - Teste de seleção

### Pré-requisito
Esta API utiliza como banco o MySQL com usuário e senha "dba_onibus", basta criar o usuário no banco com as permissões que a própria API cria o Schema e tabelas.

## Rodando os testes

### 1. API Datapoa

Estas chamadas buscam dados da API http://datapoa.com.br/group/about/mobilidade.

- GET
   
```{host}:{porta}/linhas```

Retorna todas as linhas de ônibus do endereço http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o.

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
    "assunto": "ALGUM_ASSUNTO"
}
```

Este método salva na base de dados uma nova pauta.

- PUT

```{host}:{porta}/pautas/{id}/iniciar```

Este método inicia uma pauta, por default é iniciada com duração de 1 minuto, mas pode ser adicionado um tempo em minutos no parametro "espera".

```{host}:{porta}/pautas/{id}/iniciar?espera=x```

### 2. Associados

- GET

```{host}:{porta}/associados```

Chamada que retorna todos os associados cadastrados na base de dados.

- POST

```{host}:{porta}/associados```

Body:
```
 {
        "nome": "NOME_DO_ASSOCIADO"
}
```

Este método cadastra um novo associado.

### 3. Voto

- POST

```{host}:{porta}/pauta/{id}/votar```

Body:
```
 {
    "associado": {
        "id": {ID_ASSOCIADO}
    },
    "resposta": "{RESPOSTA_SIM_OU_NAO}"
}
```

Este método cadastra um voto para a pauta selecionada, os votos podem ser vistos na chamada de todas as pautas ou na chamada da pauta pelo ID.

## Ferramentas utilizadas
- IDE: SpringTool
- Maven
- Spring Boot
- Validation (Javax)
- Spring Data
- Flyway
- Spring web
- MySQL Driver
- ModelMapper

