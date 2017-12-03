## Viikkoraportti 4

Tällä viikolla implementoin solujen erottamisen toisistaan. Ideana oli käyttää aputietorakenteena
kekoa, joten implementoin myös sen. Keon kanssa tuli ongelmaksi solujen collisionin päivittäminen,
niiden siirtämisen jälkeen. Tämän takia en vielä käytä kekoa vaan listoja, joka taas vaikuttaa
negatiivisesti aikavaativuuksiin. Toistaiseksi solujen erotus toisistaan ei toimi jos niitä on enemmän
kuin 2. Syyhyn en ole vielä päässyt käsiksi, se on siis ensi viikon homma. Toivon saavani ensi
viikolla keon käyttöön, korjattua tämän hetkiset bugit sekä tehtyä delaunay triangulationin. En ole
myöskään tyytyväinen koodin laatuun, joten koitan refaktoroida sitä, sekä parantaa aikavaativuuksia.
Käytin tällä viikolla aikaa noin 15h.