## Viikkoraportti 4

T�ll� viikolla implementoin solujen erottamisen toisistaan. Ideana oli k�ytt�� aputietorakenteena
kekoa, joten implementoin my�s sen. Keon kanssa tuli ongelmaksi solujen collisionin p�ivitt�minen,
niiden siirt�misen j�lkeen. T�m�n takia en viel� k�yt� kekoa vaan listoja, joka taas vaikuttaa
negatiivisesti aikavaativuuksiin. Toistaiseksi solujen erotus toisistaan ei toimi jos niit� on enemm�n
kuin 2. Syyhyn en ole viel� p��ssyt k�siksi, se on siis ensi viikon homma. Toivon saavani ensi
viikolla keon k�ytt��n, korjattua t�m�n hetkiset bugit sek� tehty� delaunay triangulationin. En ole
my�sk��n tyytyv�inen koodin laatuun, joten koitan refaktoroida sit�, sek� parantaa aikavaativuuksia.
K�ytin t�ll� viikolla aikaa noin 15h.