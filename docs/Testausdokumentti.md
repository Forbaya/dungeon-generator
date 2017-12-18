## Testausdokumentti
Suurinta osaa luokista on testattu kattavasti. Ainoastaan luokkia Corridor ja EdgeWithCells ei ole testattu, sillä ne ovat erittäin yksinkertaisia aputietorakenteita.
Private näkyvyyden omaavia metodeita on testattu käyttäen apuna Javan tarjoamaa reflection APIa. Näissä tilanteissa määritetään Method-muuttuja käyttäen getDeclaredMethod-metodia. Tämän jälkeen Method-muuttuja asetetaan kutsuttavaksi kutsumalla sille setAccessible(true). Nyt metodia voidaan kutsua invoke-metodilla.

Loin TestHelper-luokan jonka testiluokat extendaavat. TestHelper tarjoaa assertEqualsWithMessage-metodin, jonka avulla voidaan helposti tulostaa virheellisessä tilanteessa expected ja actual arvot, jotka annetaan parametrina.

Ajan puutteen takia Dungeon-luokasta jäi testaamatta delaunay triangulationin luominen, Primin algoritmi ja L-muotoisten käytävien luominen. Kaikki näistä algoritmeista on melko monimutkaisia, ja niiden testaus olisi ollut melko haastavaa.