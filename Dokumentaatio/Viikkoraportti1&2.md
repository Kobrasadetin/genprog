##Viikkoraportti 1/2
19.9.2016



####Mitä olen tehnyt tällä viikolla?
Suuri osa ajasta on kulunut geneettiseen ohjelmointiin tutustuessa. Tavanomaisesti geneettisessä ohjelmoinnissa käytetään puurakennetta - tällä tavalla toteutettu ohjelma voitaisiin esittää muodossa, joka muistuttaa esimerkiksi Clojure-ohjelmointikieltä. Tästä poiketen aion käyttää ensimmäisessä versiossa lineaarista rakennetta, kuitenkin niin että ohjelmiston (ja evolutiivisen algoritmin) kannalta ei ole merkityksellistä onko Genotype-luokan sisäinen toteutus lineaarinen vai puumallinen. 
####Miten ohjelma on edistynyt?
Toistaiseksi varsinaista toiminnallisuutta ja sen testaamista on hyvin vähän. Ohjelman rakenne kuitenkin alkaa muotoutua. 
####Mitä opin tällä viikolla / tänään?
Opin että Java ei tue delegaatteja. Olen tehnyt viimeksi ohjelmointia C#-ympäristössä, ja operaatiosolmujen toteuttaminen funktiodelegaateilla olisi tuntunut luontevalta. Nyt valitsin ratkaisuksi sisäiset luokat, jotka oletettavasti ovat "javamaisin" tapa tehdä sama asia. Myöhemmin olisi hauska kokeilla ohjelmien rakentamista bytecodesta ja tutkia vaikutusta ohjelmiston suorituskykyyn.
####Mitä teen seuraavaksi?
Seuraavaksi teen yksikkötestit valmiiksi ja toteutan evolutiivisen algoritmin, jotta ohjelmiston integraatiotestaus voidaan aloittaa.
