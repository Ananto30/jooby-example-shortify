# shortify

An example app using [Jooby](https://www.jooby.org/). Followed a modular approach, somewhat like Spring using annotations.

Inspired by this [benchmark](https://www.techempower.com/benchmarks/#section=data-r19&hw=cl&test=composite).

## running

    ./gradlew joobyRun

## building

    ./gradlew build

## docker

     docker build . -t shortify
     docker run -p 8080:8080 -it shortify
