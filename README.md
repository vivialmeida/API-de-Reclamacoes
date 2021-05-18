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

2. Empresa
- [X] Buscar empresas
- [X] Buscar empresa por nome



**Coleção:** 

Collection da API no Postman:

Environment da API no Postman:

**Hosts:**

Local: localhost:8080/

Server: https://appreclame-aqui.herokuapp.com/


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




