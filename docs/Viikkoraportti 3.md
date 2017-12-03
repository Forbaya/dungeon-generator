## Viikkoraportti 3

T�m� viikko meni suurimmaksi osaksi algoritmin keksimiseen, joka erottaa kaikki solut toisistaan.
En l�yt�nyt siihen mit��n valmista algoritmia, joten kehitin siihen oman. Algoritmin toiminta on
seuraavanlainen:

Toista niin kauan kun soluja on p��lek�in
1. Valitse solu, joka on toisen solun kanssa p��lek�in, jonka keskipiste on l�himp�n�
ympyr�n keskipistett� (jonka sis�lle solut generoidaan).
2. Valitse solu, joka on kohdassa 1. valitun solun kanssa p��lek�in, ja jonka
keskipiste on l�himp�n� ympyr�n keskipistett�.
3. Siiret��n kohdassa 2. valittua solua (kauempaa ympyr�n keskipisteest�) pois p�in
ympyr�st� sen akselin mukaan, jolla huoneet leikkaa v�hemm�n.
4. P�ivitet��n solujen tiedot niiden p��ll� olevista soluista.

Algoritmi ei varmasti ole tehokkain, joka t�m�n ongelman ratkaisee, mutta toistaiseksi en ole
keksinyt parempaa. Sen aikavaativuutta en ole viel� analysoinut. En saanut viel� toteutettua itse
algoritmia, mutta nyt kun idea on selvill�, saan sen pian tehty�.
Sain t�ll� viikolla toteutettua soluille collision detectionin, tein testej� ja lis�sin dokumentaatiota.
L�ysin my�s jokusen bugin jotka sain korjattua. Tein my�s oman toteutukseni ArrayListille. T�m�
oli t�h�n menness� hankalin viikko, sill� jouduin kehitt�m��n solujen erottamiseen oman algoritmin.
Ensi viikon teht�v�t ovat t�h�n menness� melko selv�t. Yll� olevan algoritmin toteuttaminen, sek�
minimikeko-tietorakenteen toteuttaminen, jota tulen k�ytt�m��n solujen valitsemiseen. Aikaa k�ytin
t�ll� viikolla ty�h�n noin 20 tuntia, josta suuri osa algoritmin suunnitteluun. Pyrin saamaan
suunnittelemani algoritmin toteutettua ennen ensimm�ist� vertaisarviointia, jotta koodissani olisi
sent��n jotain algoritmillista.