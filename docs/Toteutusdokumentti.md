## Toteutusdokumentti
Algoritmi koostuu monesta vaiheesta, jotka määriteltiin määrittelydokumentissa. Tässä dokumentissa pyrin analysoimaan toteutettujen algoritmieni aikavaativuutta, sekä ohjelmani puutteita.

1. Solujen generointi
Oletin, että pystyn generoimaan solut O(n) ajassa solujen määrän suhteen. En kuitenkaan ottanut silloin huomioon, että joudun pitää kirjaa siitä minkä solujen kanssa solut ovat päällekäin. Vertaan siis uuden solun luonnissa uutta solua kaikkiin vanhoihin, joten päädyttiin aikavaativuuteen O(n^2).
2. Solujen erottaminen toisistaan.
Kuten määrittelydokumentissa sanoin, tämän algoritmin analysointi on sen verran hankalaa ja riippuu useista parametreista, ettei oma taitoni siihen tällä hetkellä pysty.
3. Delaunay triangulation algoritmin toteutuksessa käytän Bowyer-Watson algoritmia. Kyseinen algoritmi on melko monimutkainen, ja sen aikavaativuuden analysointi on myös minulle haastavaa. Toteutin Wikipedia-artikkelin pseudokoodin. Artikkelin mukaan triangulatee n pistettä ajassa O(n log n), mutta joissain caseissa menee O(n^2). Olettaisin siis, että toteutukseni toimii keskimäärin ajssa O(n log n). Tein tälle suorituskykytestausta, ja algoritmini toimi nopeasti jopa 500 solulla.
4. Pyrin tekemään Primin algoritmin vieruslistalla, jolloin oltaisiin päästy aikavaativuuteen O(E log V). Kuten määrittelydokumentissa mainitsin, minulla tuli jälleen ongelmia minimikekototeutukseni kanssa, joten päädyin toteuttamaan Primin vierusmatriisilla. Aikavaativuudeksi tuli siis O(V^2).
5. L-muotoisten käytävien generoimiseen menee O(E) aikaa Primin tekemien kaarien suhteen. Todellisuudessa huoneita on yleensä kiinni toisissaan, jolloin aikaa menee hieman vähemmän.