# SICREDI API - Teste de seleção

### pré-requisito
Esta API utiliza como banco o MySQL com usuário e senha "sicredi", basta criar o usuário no banco com as permissões que a própria API cria o Schema e tabelas.

## Rodando os testes

1. Pautas

- GET
   
```{host}:{porta}/pautas```

Este método retorna todas as pautas armazenadas na base de dados.

- POST
```{host}:{porta}/pautas```

Body:
```
{
    "assunto": "Algum assunto"
}
```
