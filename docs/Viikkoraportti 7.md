## Viikkoraportti 7
Kurssin viimeinen viikko on koittanut. Dungeon generation algoritmin toteutus on ollut minulle opettava kokemus. Algoritmi koostuu monesta osasta, ja sen toteuttaminen oli iso ja haastava työ.
Kahteen vaiheeseen jouduin keksimään omat algoritmit. Toinen näistä oli algoritmi huoneiden toisista erottamiseen, ja toinen L-muotoisten käytävien tekemiseen. Kummankaan suunnittelu/toteutus ei ollut helppo, enkä saanut niitä oikein testattua.
Kummatkin kuitenkin tuntuvat toimivan hyvin.

Tällä viikolla sain tehtyä delaunay triangulationin luomista kolmioista (verkosta) minimal spanning treen, jonka avulla sain tehtyä L-muotoiset käytävät sellaisten huoneiden väliin jotka eivät ole kiinni toisissaan.
Haasteita tuli molempien kanssa. Toteutun minimal spanning treen Primin algoritmilla, jonka ikävä kyllä jouduin toteuttamaan ajan puutteissa vierusmatriisilla. Olisin halunnut toteuttaa sen vieruslistalla, mutta oman kekototeutukseni kanssa tuli jälleen ongelmia, joten menin sieltä missä rima oli matalampi.
Olen kuitenkin tyytyväinen dungeon generointialgoritmini lopputulokseen. 