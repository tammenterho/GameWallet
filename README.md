# Sovelluksen Käyttöohjeet

## Ennakkoedellytykset

Ennen kuin aloitat, varmista, että seuraavat työkalut on asennettu ja konfiguroitu koneellasi:

- **Java 17**: Sovellus on kehitetty käyttäen Java 17:ää. Jos sinulla ei vielä ole Java 17:ää asennettuna, voit ladata ja asentaa sen [AdoptOpenJDK](https://adoptopenjdk.net/) tai [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) virallisilta sivuilta.

  Voit tarkistaa Java-version komentoriviltä komennolla:

  ```bash
  java -version
  ```

  Sinun tulisi nähdä viesti, joka vahvistaa Java 17:n olevan käytössä.

- **Maven**: Mavenia käytetään riippuvuuksien hallintaan ja projektin rakentamiseen, varmista, että Maven on asennettu. Mavenin asennusohjeet löytyvät [Mavenin virallisilta sivuilta](https://maven.apache.org/install.html).

  Tarkista Mavenin asennus komennolla:

  ```bash
  mvn -v
  ```

  Komennon tulisi tulostaa Mavenin versio ja Java-kotihakemisto.

Tässä dokumentissa kuvataan, miten voit asentaa, käyttää ja testata Spring Boot -pohjaista sovellusta. Sovellus on saatavilla GitHubissa, ja se tukee RESTful API -kutsuja pelien ostamiseen ja voittojen keräämiseen.

## Sovelluksen Asennus

### 1. Lataa projekti GitHubista

Aloita kloonaamalla projekti GitHub-repositoriosta omalle koneellesi. Voit tehdä tämän avoimella terminaalilla ja suorittamalla seuraavan komennon:

```bash
git clone https://github.com/tammenterho/GameWallet.git
```

### 2. Sovelluksen käynnistäminen

```bash
cd gameWallet
mvnw spring-boot:run
```

Sovellus käynnistyy porttiin https://localhost:8443

### 3. APIen testaaminen Postmanilla

Sovelluksessa oletetaan että, eventId ja winEventId tulevat pelimoottorilta.

#### Pelin osto

1. Avaa Postman ja luo uusi pyyntö.
2. Aseta pyynnön tyyppi PUT ja URL

```
**https://localhost:8443/players/1/purchase/2**
```

Korvaa tarvittaessa pelaajan ID ja pelin ID. 3. Valitse Body-välilehti, valitse raw ja sitten JSON. 4. Syötä bodyyn ostettavan pelin hinta JSON-muodossa, esimerkiksi: **50**. Tietokannassa olevalla pelaajalla on aina 500 aloitusrahaa. Mikäli ostat pelejä yli 500:lla rahalla, osto ei tapahdu. 5. Lähetä pyyntö ja tarkastele vastausta. 6. Voit tehdä useamman pyynnön, mutta muista vaihtaa URL osoitteen viimeinen numero, joka on tapahtuman tunniste eventId. 7. Sovelluksen tulisi palauttaa Balance:n määrä.

#### Voittojen kerääminen

1. Luo uusi pyyntö Postmanissa.
2. Aseta pyynnön tyyppi PUT ja URL

```
**https://localhost:8443/players/1/win/5**
```

Korvaa tarvittaessa pelaajan ID ja voiton ID. Mikäli et ole käynnistänyt sovellusta uudelleen "Pelin oston" testaamisen jälkeen, tee pyynnöt eri eventId:llä. Eli katso ettei viimeinen numero ole sama kuin mitä käytit "Pelin ostoa" testatessa tai mitä olet jo käyttänyt voittojen keräämiseen. 3. Valitse Body, valitse raw ja sitten JSON. 4. Syötä bodyyn kerättävän voiton määrä JSON-muodossa, esim: 1000. 5. Lähetä pyyntö ja tarkastele vastausta. Mikäli pidät sovelluksen käynnissä, voit käyttää uusia voittorahojasi uusien pelien ostamiseen.

### Testien Suorittaminen JUnitilla

Sovelluksen mukana tulee joukko JUnit-testejä, jotka voit suorittaa varmistaaksesi, että sovelluksen perustoiminnot toimivat odotetulla tavalla.

1. Avaa terminaali projektikansiossa.
2. Suorita testit komennolla:

```bash
mvnw test
```

Testit suoritetaan, ja saat yhteenvedon suoritetuista testeistä sekä tiedon, menivätkö ne läpi vai eivät.

### H2 tietokanta

H2 Tietokanta on Java-pohjainen relationaalinen tietokanta, joka on suunniteltu olemaan erittäin nopea, kevyt ja helppokäyttöinen. H2-tietokanta toimii suoraan sovelluksen kanssa samassa JVM:ssä (Java Virtual Machine). Tämä tarkoittaa, että tietokantaan pääsee käsiksi vain kyseisen sovelluksen kautta, ja se on käytettävissä ainoastaan sovelluksen ollessa käynnissä. Tämä moodi sopii erinomaisesti kehitys- ja testausympäristöihin, koska se ei vaadi erillisen tietokantapalvelimen pystyttämistä ja konfigurointia.
