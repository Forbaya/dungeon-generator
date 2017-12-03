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