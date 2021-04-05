FROM ubuntu:20.10

RUN apt update
RUN apt install -y curl gnupg
RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
RUN curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add
RUN apt-get update
RUN apt-get install -y sbt
RUN apt install -y openjdk-8-jdk

RUN apt install git
RUN git clone https://github.com/gaqp/justa-desafio
WORKDIR /justa-desafio
RUN sbt dist
RUN apt install -y unzip 
RUN unzip ./target/universal/justa-desafio-1.0-SNAPSHOT.zip
WORKDIR /justa-desafio/justa-desafio-1.0-SNAPSHOT/bin/
EXPOSE 9000
CMD ["./justa-desafio","-Dplay.http.secret.key='QCY?tAnfk?aZ?iwrNwnxIlR6CTf:G3gf:90Latabg@5241AB`R5W:1uDFN];Ik@n'"]