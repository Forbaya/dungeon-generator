## M‰‰rittelydokumentti

Toteutan luolaston generointialgoritmin Javalla. Luolastojen generointiin on useita erilaisia algoritmeja. Mielenkiintoisin lˆyt‰m‰ni on kuitenkin t‰m‰. Toteuttamani algoritmi perustuu seuraavaan [linkkiin](https://www.reddit.com/r/gamedev/comments/1dlwc4/procedural_dungeon_generation_algorithm_explained/)

### Algoritmi koostuu useammasta vaiheesta.
1. Valitaan generoitavien solujen m‰‰r‰. Jos solu on tarpeeksi suuri, kutsutaan sit‰ huoneeksi. Solut generoidaan tietyn s‰teen omaavan ympyr‰n sis‰‰n. T‰ss‰ vaiheessa solut ovat eritt‰in todenn‰kˆisesti p‰‰llek‰in. T‰m‰ vaihe onnistuu lineaarisessa ajassa O(n^2), sill‰ t‰ytyy tarkistaa onko uusi solu vanhojen solujen p‰‰ll‰, ja m‰‰ritt‰‰ soluille collision jos n‰in on.
2. Solut ovat generoimisen j‰lkeen todenn‰kˆisesti p‰‰llek‰in. Kehitin t‰h‰n oman algoritmin, joka menee karkeasti n‰in:
  1. Toista niin kauan kuin soluja on p‰‰lek‰in
  2. Valitse solu, jonka keskipiste on l‰himp‰n‰ ympyr‰n keskipistett‰
  3. Valitse solu, joka on ensimm‰isen solun p‰‰ll‰, ja jonka keskipiste on l‰himp‰n‰ ensimm‰isen solun keskipistett‰
  4. Erota toiseksi valittu solu tyˆnt‰m‰ll‰ sit‰ pois p‰in ympyr‰n keskipisteest‰ sen akselin mukaan, joka leikkaa v‰hemm‰n.
  T‰m‰n algoritmin aikavaativuutta on vaikea arvioida, sill‰ kun huonetta siirret‰‰n, se voi olla silti vaikka kuinka monen huoneen p‰‰ll‰ riippuen parametreist‰ kuten ympyr‰n s‰teen pituus, solujen m‰‰r‰ sek‰ solujen maksimi ja minimikoot.
3. Solut ovat nyt erill‰‰n, ja tarpeeksi suuret solut ovat huoneita. Nyt huoneista tehd‰‰n verkko tekem‰ll‰ îdelanuay triangulationî. Wikipedian mukaan t‰m‰ pit‰isi onnistua ajassa O(n log n) huoneiden m‰‰r‰‰n n‰hden.
4. Sill‰ kaikkien huoneiden ei haluta olla yhteydess‰ toisiinsa, tehd‰‰n verkosta viritt‰v‰ puu k‰ytt‰en Primin algoritmia. Primin algoritmin aikavaativuus pit‰isi onnistua ajassa O(E log V), jossa E on kaarien m‰‰r‰ ja V on solmujen m‰‰r‰.
5. Lopuksi lis‰t‰‰n L-muotoisia k‰yt‰vi‰ menem‰‰n huoneista toiseen, jos huoneet eiv‰t ole vierek‰in. Olettaisin, ett‰ t‰m‰ onnistuu lineaarisessa ajassa O(n) huoneiden m‰‰r‰‰n n‰hden.

Aputietorakenteina tarvitsen ArrayListin kaltaista listarakennetta, taulukoita, verkkoja. Syˆtteiksi ohjelma saa solujen m‰‰r‰n, niiden minimi- ja maksimikoot ja ympyr‰n s‰teen, jonka sis‰lle solut aluksi generoidaan.

### L‰hteet:
[Delaunay triangulationin Wikipedia-artikkeli](https://en.wikipedia.org/wiki/Delaunay_triangulation)

[Tietorakenteet ja algoritmit -kurssimateriaali](https://www.cs.helsinki.fi/u/floreen/tira2013/tira.pdf)