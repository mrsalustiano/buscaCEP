🚀 Aplicação de Pesquisa de CEP com Spring Boot

📌 Sobre o Projeto

Este projeto é uma aplicação em Spring Boot que realiza a pesquisa de CEP utilizando a API do ViaCEP, armazena as consultas em um banco de dados MySQL e expõe um endpoint REST.


🛠 Tecnologias Utilizadas

Java 17
Spring Boot 3
Spring Data JPA
Spring Cloud OpenFeign (para consumir a API do ViaCEP)
Banco de Dados MySQL
Lombok

📡 Como Usar a API

Após rodar a aplicação, acesse no navegador ou Postman:


`` GET http://localhost:8080/api/cep/02611-001 ``


 Resposta esperada (JSON)

 ``` 
 {
    "cep": "02611-001",
    "logradouro": "Avenida Parada Pinto",
    "complemento": "de 1502 ao fim - lado par",
    "bairro": "Vila Nova Cachoeirinha",
    "localidade": "São Paulo",
    "uf": "SP",
    "ibge": "3550308",
    "gia": "1004",
    "ddd": "11",
    "siafi": "7107"
}
```


