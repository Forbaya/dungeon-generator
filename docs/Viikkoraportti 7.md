## Viikkoraportti 7
Kurssin viimeinen viikko on koittanut. Dungeon generation algoritmin toteutus on ollut minulle opettava kokemus. Algoritmi koostuu monesta osasta, ja sen toteuttaminen oli iso ja haastava ty�.
Kahteen vaiheeseen jouduin keksim��n omat algoritmit. Toinen n�ist� oli algoritmi huoneiden toisista erottamiseen, ja toinen L-muotoisten k�yt�vien tekemiseen. Kummankaan suunnittelu/toteutus ei ollut helppo, enk� saanut niit� oikein testattua.
Kummatkin kuitenkin tuntuvat toimivan hyvin.

T�ll� viikolla sain tehty� delaunay triangulationin luomista kolmioista (verkosta) minimal spanning treen, jonka avulla sain tehty� L-muotoiset k�yt�v�t sellaisten huoneiden v�liin jotka eiv�t ole kiinni toisissaan.
Haasteita tuli molempien kanssa. Toteutun minimal spanning treen Primin algoritmilla, jonka ik�v� kyll� jouduin toteuttamaan ajan puutteissa vierusmatriisilla. Olisin halunnut toteuttaa sen vieruslistalla, mutta oman kekototeutukseni kanssa tuli j�lleen ongelmia, joten menin sielt� miss� rima oli matalampi.
Olen kuitenkin tyytyv�inen dungeon generointialgoritmini lopputulokseen. 