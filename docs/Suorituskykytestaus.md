## Suorituskykytestaus

### 3.12.2017
Tällä hetkellä Delaunay triangulation ei ole vielä täysin valmis, joten suorituskykytestaus sisältää solujen luonnin, sekä niiden erottamisen toisistaan.
Alla on taulukko solujen määristä ja niiden generointiin sekä erottamiseen mennyt aika. Suoritin jokaisella solumäärällä ohjelman 10 kertaa, ja laskin niiden keskiarvon.

| Solujen määrä | Aika          |
| ------------- | ------------- |
| 10            | 6 ms          |
| 100           | 81 ms         |
| 200           | 642 ms        |
| 300           | 2440 ms       |
| 400           | 6874 ms       |

Testeissäni ympyrän säde oli 128 pikseliä, yhden tiilin koko 16 pikseliä, pienin solun sivu 32 pikseliä ja suurin solun sivu 160 pikseliä. Sillä ympyrän säde on noin pieni, tulee collidevia soluja erittäin paljon kun soluja generoidaan yli 100. Tämä vastaa pahimpia tapauksia solujen erotusalgoritmissani. Kuten taulukosta huomataan, alkaa yli sadan solun jälkeen algoritmia hidastua ikävän paljon. Aikavaativuus on siis kehno! Tämä oli kuitenkin odotettavissa, onhan algoritmi itse keksimäni, enkä ole saanut toteutettua sitä minimikeon kanssa.

### 10.12.2017
Testasin ArrayListini suorituskykyä Javan ArrayListiin. Tein omat testit integerien lisäämiselle ja poistamiselle.

#### Lisäys

| Lukujen määrä | Oman toteutuksen aika | Javan toteutuksen aika |
| ------------- | --------------------- | ---------------------- |
| 1000          | 1 ms                  | 0 ms                   |
| 10000         | 1 ms                  | 1 ms                   |
| 100000        | 8 ms                  | 3 ms                   |
| 1000000       | 17 ms                 | 15 ms                  |
| 10000000      | 203 ms                | 1748 ms                |
| 100000000     | 21988 ms              | 29013 ms               |

Pienillä syötteillä Javan toteutus toimi nopeammin, mutta jostain syystä suurilla syötteillä omani voitti Javan toteutuksen.

#### Poisto

| Lukujen määrä | Oman toteutuksen aika | Javan toteutuksen aika |
| ------------- | --------------------- | ---------------------- |
| 1000          | 3 ms                  | 0 ms                   |
| 10000         | 25 ms                 | 3 ms                   |
| 100000        | 1910 ms               | 193 ms                 |
| 1000000       | 188244 ms             | 22571 ms               |

Lukujen poisto taas toimi jokaisella syötteellä nopeammin Javan toteutuksella. 1000000 elementin poistossa ero oli todella suuri. Oma toteutukseni poisti elementit ~3,1 minuutissa, kun Javan toteutus teki sen ~22,5 sekunnissa.

#### Delaunay triangulation
Delaunay triangulation toimi tehokkaasti

| Huoneiden määrä | Aika   |
| --------------- | ------ |
| 100             | 26 ms  |
| 200             | 45 ms  |
| 300             | 50 ms  |
| 400             | 87 ms  |
| 1000            | 134 ms |
