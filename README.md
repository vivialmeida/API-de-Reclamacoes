# Challenge-Backend-ReclameAQUI

### Recursos API
1. Reclamação
- [X] Cadastrar reclamação
- [X] Listar reclamações
- [X] Buscar reclamação por id
- [X] Excluir reclamação
- [X] Buscar reclamacões em um intervalo de datas
- [X] Buscar reclamacões por empresa
- [X] Buscar reclamacões por localidade
- [X] Buscar reclamacões por cidade e empresa


2. Empresa
- [X] Buscar empresas
- [X] Buscar empresa por nome



**Coleção:** 

Collection da API no Postman: https://github.com/vivialmeida/Challenge-Backend-ReclameAQUI/blob/master/src/main/resources/postman/reclame_aqui_postman_collection.json

Environment da API no Postman: https://github.com/vivialmeida/Challenge-Backend-ReclameAQUI/blob/master/src/main/resources/postman/reclame_aqui_postman_environment.json

**Hosts:**

Local: localhost:8080/

Server: https://appreclame-aqui.herokuapp.com/ (Geralmente a primeira request ao servidor, demora aproximadamente 50s, após isso o retorno das demais requests possui um limite de tempo aceitaval)


**Documentação da API:**

https://appreclame-aqui.herokuapp.com/reclame-aqui/swagger-ui.html

**Profiles:**

PROD(default)

DEV

**Instruções:**

Para executar o programa localmente, caso queira apontar para produção:
1. Substituir a variável de ambinete ${MONGODB_URI} no arquivo application-prod por: 
    - mongodb+srv://sys:r2NfczqP8TFzAhSs@clusterdev.atpqg.mongodb.net/reclamacoes?retryWrites=true&w=majority
2. Selecionar a classe: GestorDeReclamacoesApplication.java e executar.

Para executar o programa localmente, caso queira apontar para desenvolvimento:
1. Adicionar profile "dev"
2. Selecionar a classe: GestorDeReclamacoesApplication.java e executar.


    
**Diagramas:**

1.Diagrama de classes: 


![alt text](https://github.com/vivialmeida/Challenge-Backend-ReclameAQUI/blob/master/src/main/resources/diagramas/diagrama_classes.png)


