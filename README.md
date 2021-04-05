# Desafio backend Justa
Projeto baseado no desafio da Justa
## Como excecutar o projeto
1. clone este repositório usando os comandos
 ```
git clone https://github.com/gaqp/justa-desafio
```
2. acesse o diretório do repositório clonado
 ```
 cd justa-desafio
```
### executar usando o código fonte

 ```
 sbt run
```
### executar usando o docker
Use o docker build 
 ```
 docker build -t justa ./
```
depois use execute o container
```
docker run -p 9000:9000 justa
```


O projeto escutará na porta 9000
