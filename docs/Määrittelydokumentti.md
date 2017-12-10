## M‰‰rittelydokumentti

Toteutan luolaston generointialgoritmin Javalla. Luolastojen generointiin on useita erilaisia algoritmeja. Mielenkiintoisin lˆyt‰m‰ni on kuitenkin t‰m‰. Toteuttamani algoritmi perustuu seuraavaan [linkkiin](https://www.reddit.com/r/gamedev/comments/1dlwc4/procedural_dungeon_generation_algorithm_explained/)

### Algoritmi koostuu useammasta vaiheesta.
1. Valitaan generoitavien solujen m‰‰r‰. Jos solu on tarpeeksi suuri, kutsutaan sit‰ huoneeksi. Solujen leveyden ja korkeuden m‰‰ritt‰miseen voidaan k‰ytt‰‰ Park-Miller normaalijakaumaa. En ole viel‰ varma tulenko k‰ytt‰m‰‰n sit‰. Solut generoidaan tietyn s‰teen omaavan ympyr‰n sis‰‰n. T‰ss‰ vaiheessa solut ovat eritt‰in todenn‰kˆisesti p‰‰llek‰in. T‰m‰ vaihe onnistuu lineaarisessa ajassa O(n) solujen m‰‰r‰‰n n‰hden.
2. Solut ovat generoimisen j‰lkeen todenn‰kˆisesti p‰‰llek‰in. Nyt t‰ytyy kehitt‰‰ algoritmi, jolla huoneet saadaan irti toisistaan. T‰h‰n ilmeisesti voidaan k‰ytt‰‰ apuna îseparation streering behaviouriaî. T‰m‰n aikavaativuudesta en viel‰ osaa sanoa.
3. Nyt mahdollisia tyhji‰ v‰lej‰ t‰ytet‰‰n 1x1 kokoisilla soluilla. T‰m‰n tarpeesta ja aikavaativuudesta en viel‰ osaa sanoa.
4. Solut ovat erill‰‰n, ja tarpeeksi suuret solut ovat huoneita. Nyt huoneista tehd‰‰n verkko k‰ytt‰m‰ll‰ îdelanuay triangulationiaî. Wikipedian mukaan t‰m‰ pit‰isi onnistua ajassa O(n log n) huoneiden m‰‰r‰‰n n‰hden.
5. Sill‰ kaikkien huoneiden ei haluta olla yhteydess‰ toisiinsa, tehd‰‰n verkosta viritt‰v‰ puu k‰ytt‰en Primin algoritmia. Primin algoritmin aikavaativuus on O(E log V), jossa E on kaarien m‰‰r‰ ja V on solmujen m‰‰r‰.
6. Lopuksi lis‰t‰‰n L-muotoisia k‰yt‰vi‰ menem‰‰n huoneista toiseen, jos huoneet eiv‰t ole vierek‰in. Olettaisin, ett‰ t‰m‰ onnistuu lineaarisessa ajassa O(n) huoneiden m‰‰r‰‰n n‰hden.

Aputietorakenteina tarvitsen ainakin ArrayListin kaltaista listarakennetta, taulukoita, verkkoja. Syˆtteiksi ohjelma saa solujen m‰‰r‰n ja ympyr‰n s‰teen, jonka sis‰lle solut aluksi generoidaan.

### L‰hteet:
[Delaunay triangulationin Wikipedia-artikkeli](https://en.wikipedia.org/wiki/Delaunay_triangulation)
[Tietorakenteet ja algoritmit -kurssimateriaali](https://www.cs.helsinki.fi/u/floreen/tira2013/tira.pdf)