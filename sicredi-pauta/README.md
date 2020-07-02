# SICREDI API - Teste de seleção

### Pré-requisito
Esta API utiliza como banco o MySQL com usuário e senha "sicredi", basta criar o usuário no banco com as permissões que a própria API cria o Schema e tabelas.

## Rodando os testes

### 1. Pautas

- GET
   
```{host}:{porta}/pautas```

Este método retorna todas as pautas armazenadas na base de dados.
Pode ser utilizado com um identificador para buscar somente uma pauta.

```{host}:{porta}/pautas/{id}```

- POST

```{host}:{porta}/pautas```

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

