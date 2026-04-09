# ~ LABORATORUL 3 - HOMEWORK ~

## Prezentare generala 
Pachetul org.example.homework implementeaza modelul de baza al unei retele sociale formate din persoane si companii. Fiecare entitate este reprezentata ca un Profile, poate avea relatii cu alte profile si are o importanta calculata in functie de numarul acestor relatii.
   
Modelul include:
* persoane (Person) si specializari (Programmer, Designer)
* companii (Company)
* relatii intre profile
* o retea sociala (SocialNetwork) care gestioneaza si afiseaza entitatile

## Structura claselor
### 1. Profile (interfata)

Interfata comuna pentru toate entitatile din retea.

* METODE:
``` java
getName() // numele profilului
```

``` java
getImportance() // importanta (numarul de relatii)
```

``` java
getRelationships() // relatiile profilului
```
Aceasta interfata permite tratarea uniform a persoanelor si companiilor.

### 2. Person (abstract)

Clasa de baza pentru toate tipurile de persoane.

* PROPRIETATI:
- name - numele persoanei
- birthdate - data nasterii
- relationships - Map<Profile, String> cu relatiile persoanei

* FUNCTIONALITATE:
``` java 
addRelationship(Profile, String) // adauga o relatie
```

``` java 
getImportance() // numarul de relatii
```

``` java 
toString() // afiseaza numele, data nasterii si importanta
```

### 3. Programmer (extinde Person)

Reprezinta un programator.

* PROPRIETATE SPECIFICA:

favoriteLanguage - limbajul preferat

### 4. Designer (extinde Person)

Reprezinta un designer.

* PROPRIETATE SPECIFICA:

tool - instrumentul principal folosit (ex.: Figma)

### 5. Company (implementeaza Profile)

Reprezinta o companie de retea.

* PROPRIETATI:
- name - numele companiei
- industry - domeniul de activitate
- relationships - relatiile cu persoane sau alte companii

* FUNCTIONALITATE:

``` java 
addRelationship(Profile, String) // adauga o relatie
```

``` java 
getImportance() // numarul de relatii
```

``` java 
toString() // afiseaza numele, industria si importanta
```

### 6. SocialNetwork

Clasa care gestioneaza intreaga retea sociala.

* FUNCTIONALITATE:

``` java 
addProfile(Profile) // adauga un profil in retea
```
``` java 
printNetwork() // afiseaza profilele ordonate descrescator dupa importanta
```
``` java 
buildGraph() // construieste un graf neorientat al relatiilor dintre profile
```

Graful returnat este folosit ulterior in partea de Advanced pentru analiza conectivitatii.

### 7. Exemplu de utilizare (din Main.java)

Programul creeaza persoane, companii si relatii, apoi afiseaza reteaua:

```java
SocialNetwork network = new SocialNetwork();
network.addProfile(p1);
network.addProfile(p2);
network.addProfile(d1);
network.addProfile(c1);
network.addProfile(c2);

network.printNetwork();
```

Rezultatul este o lista de profile ordonate dupa importanta.



# ~ LABORATORUL 3 - ADVANCED ~

Pachetul org.example.advanced extinde modelul de retea sociala din Homework prin introducerea unor algoritmi avansati de analiza a grafurilor. Scopul este identificarea:
* punctelor de articulatie (articulation points)
* componentelor biconexe (biconnected components)

Aceste analize sunt esentiale pentru a intelege cat de robusta este reteaua si care sunt nodurile critice a caror eliminare ar fragmenta graful.

Functionalitatea este implementata in clasa ConnectivityAnalyzer, folosind algoritmul clasic al lui Tarjan, bazat pe parcurgere DFS.
   
## ConnectivityAnalyzer
Clasa primeste o instanta de SocialNetwork si construieste automat graful neorientat al relatiilor folosind metoda network.buildGraph().

Graful rezultat este reprezentat ca: Map<Profile, List<Profile>> ; unde fiecare relatie este adaugata in ambele directii.

### 1. Identificarea Punctelor de Articulatie

Metoda: 
``` java 
public <List<Profile> findArticulationPoints()
```

Un punct de articulatie este un nod a carui eliminare creste numarul de componente conexe ale grafului. Intr-o retea sociala, aceste noduri sunt entitati critice care conecteaza grupuri altfel separate.

* ALGORITMUL FOLOSESTE:

- discoveryTime - momentul descoperirii nodului in DFS
- low - cel mai mic discovery time accesibil prin muchii de intoarcere
- parent - parintele in arborele DFS
- articulationPoints - setul final de noduri critice

* CONDITIILE TARJAN IMPLEMENTATE:

- radacina DFS cu >= 2 copii
- low[v] >= discoveryTime[u] pentru un copil v

Daca oricare dintre conditii este indeplinita, nodul este marcat ca punct de articulatie.
       
### 2. Determinarea Componentelor Biconexe

Metoda: 
```java
public List<List<Profile>> findBiconnectedComponents()
```

O componenta biconexa este o submultime de noduri care ramane conexa chiar daca eliminam orice nod din ea. Aceste componente reprezinta zone stabile ale retelei.

## ALGORITMUL FOLOSESTE:
- aceleasi structuri discoveryTime, low, parent
- o stiva de muchii (edgeStack) pentru a extrage componentele

* MECANISMUL:

Cand algoritmul detecteaza o separare (low[v] >= discoveryTime[u]), extrage din stiva toate muchiile pana la (u, v) si formeaza o componenta biconexa.
       
## Teste Junit

Fisierul SocialNetworkTest.java valideaza corectitudinea algoritmilor.

### 1. Testarea punctelor de articulatie

Testul confirma ca:

- Alice, TechCorp si Bob sunt puncte de articulatie
- Charlie si DesignHub nu sunt

Acest lucru reflecta structura reala a grafului.

### 2. Testarea componentelor biconexe

Testul verifica:

- fiecare componenta are cel putin doua noduri
- suma nodurilor din componente >= numarul total de profile

Aceste conditii sunt corecte pentru un graf neorientat cu multiple componente biconexe.



# ~ LABORATORUL 4 - COMPULSORY ~

Aceasta sectiune implementeaza un model simplu al unui oras, in care:
* intersectiile sunt noduri ale grafului,
* strazile sunt muchii cu nume si lungimi, 
* iar scopul este pregatirea datelor pentru un algoritm de tip Minimum 

Spanning Tree (MST), necesar pentru instalarea cablurilor de supraveghere
Cerinta este rezolvata folosind colectii Java adecvate si Java Stream API.

### 1. Modelul obiectelor

* Intersection

Reprezinta o intersectie din oras.

==> CARACTERISTICI:

- are un singur atribut: name
- este comparabila (Comparable<Intersection>)
- este hashable (prin @EqualsAndHashCode)
- este afisabila (@ToString)

Acest lucru permite:

- sortarea intersectiilor
- stocarea lor intr-un HashSet fara duplicate

* Street

Reprezinta o strada care conecteaza doua intersectii.

==> CARACTERISTICI:

- name - numele strazii
- length - lungimea strazii
- firstIntersection, secondIntersection - capetele strazii
- este comparabila (Comparable<Street>) dupa lungime

Acest lucru permite sortarea strazilor intr-o LinkedList.

### 2. Generarea intersectiilor cu Stream API

Intersectiile sunt generate folosind Java Stream API:

```java
List<Intersection> intersections = 
	IntStream.rangeClosed(1, 10)
		.mapToObj(i -> new Intersection("I" + i))
		.toList();

```

Aceasta demonstreaza:
* folosirea IntStream
* generarea obiectelor cu mapToObj
* colectarea intr-o lista

### 3. Crearea si Sortarea Strazilor

Strazile sunt stocate intr-o: LinkedList<Street>

Sortarea se face cu un comparator lambda: 

```java
streets.sort((s1, s2) -> Integer.compare(s1.getLength(), s2.getLengt()));
```

Aceasta respecta cerinta de a folosi:
* LinkedList
* un comparator exprimat ca lambda
* obiecte comparabile

### 4. Set de intersectii - Eliminarea duplicatelor

Pentru a demonstra proprietatile unui Set, se foloseste:

```java
Set<Intersection> intersectionSet = new HashSet<>(intersections);
intersectionSet.add(new Intersection("I1"));
```

Datorita implementarii equals() si hashCode() in Intersection, obiectul duplicat nu este adaugat.

### 5. Exemplu de rulare (din Main.java)

Programul:
* genereaza 10 intersectii
* creeaza strazi intre ele
* sorteaza strazile dupa lungime
* demonstreaza eliminarea duplicatelor intr-un HashSet

Output-ul afiseaza:
* lista intersectiilor
* lista strazilor sortate
* continutul setului
* numarul final de intersectii din set



# ~ LABORATORUL 5 - COMPULSORY ~

Aceasta sectiune implementeaza un model orientat pe obiecte pentru gestionarea unui catalog de resurce (carti, articole, fisiere locale sau resurse online). Scopul este:
* definirea unei structuri de date pentru resurse
* crearea unui repository care gestioneaza aceste resurse
* validarea si cautarea resurselor
* deschiderea lor folosind clasa Desktop (browser sau fisier local)

Modelul este impartit in pachete clare: model, repository, exceptions, app.

## 1. Modelul obiectelor 

### Resource

Reprezinta o resursa de catalog.

==> Caracteristici:

- id - identificator unic 
- title - titlul resursei
- location - URL sau cale locala
- year - anul publicarii
- author - autorul resursei

Clasa foloseste Lombok (@Data, @AllArgsConstructor, @NoArgsConstructor) pentru generarea automata a metodelor standard.

## 2. Exceptii personalizate

### InvalidResourceException

Aruncata atunci cand o resursa nu este valida (ex: ID lipsa sau gol)

### ResourceNotFoundException

Aruncata atunci cand:
* resursa nu exista in catalog
* fisierul local nu poate fi gasit
* URL-ul nu este accesibil

Aceste exceptii asigura un flux robust si clar al erorilor.

## 3. Catalog - Repository-ul de resurse

Clasa Catalog gestioneaza toate operatiile asupra resurselor.

==> FUNCTIONALITATE:
```java
addResource(Resource)
```

* valideaza resursa
* o adauga in mapa
* arunca InvalidResourceException daca ID-ul este invalid

```java
getResource(String id)
```

* cauta resursa dupa ID
* arunca ResourceNotFoundException daca nu exista

```java
openResource(String id)
```

* deschide resursa folosind Desktop
* daca location incepe cu "http", deschide in browser
* altfel, incearca sa deschida fisierul local

Aceasta metoda demonstreaza utilizarea API-ului java.awt.Desktop.

## 4. Exemplu de utilizare (din Main.java)

Programul:
* creeza un catalog
* adauga doua resurse
* incearca sa deschida resursa cu ID-ul jvm25

```java
Catalog catalog = new Catalog();

catalog.addResource(new Resource(
	"knuth67",
	"The Art of Computer Programming",
	"d:/books/programming/tacp.ps",
	"1967",
	"Donald E. Knuth"
));

catalog.addResource(new Resource(
	"jvm25",
	"The Java Virtual Machine Specification",
	"https://docs.oracle.com/javase/specs/jvsm/se25/html/index.html",
	"2025",
	"Tim Lindholm & others"
));

catalog.openResource("jvm25");
```

Rezultatul: se deschide pagina oficiala a specificatiei JVM in browser.

# ~ LABORATORUL 6 -COMPULSORY ~

Aceasta sectiune implementeaza o aplicatie Java care interactioneaza cu o baza de date relationala folosind JDBC, pattern-ul DAO si un 
singleton pentru gestionarea conexiunii. Scopul este:
* crearea unei baze de date relationale pentru filme, genuri si actori 
* definirea tabelelor printr-un script SQL
* conectarea la baza de date folosind un Singleton
* implementarea unui DAO pentru entitatea Genre
* testarea functionalitatilor printr-o clasa Main

Structura proiectului este organizata in pachete clare: db, dao, model, compulsory.

## 1. Baza de date

Fisierul schema.sql defineste tabelele necesare:

### Tabelele principale:

* movies: id, title, release_date, duration, score, genre_id
* genres: id, name
* actors: id, name

### Tabele de legatura:

* movie_actors - asociaza filme cu actori (relatie many-to-many)

## 2. Conexiunea la baza de date - Singleton

### DatabaseConnection

Clasa DatabaseConnection gestioneaza conexiunea JDBC folosind pattern-ul Singleton.

=> Caracteristici:

- conexiune statica (private static Connection connection)
- constructor privat (nu poate fi instantiat)
- metoda init() stabileste conexiunea folosind DriverManager
- @Getter (Lombok) expune conexiunea catre DAO-uri

Exemplu de initializare:
```java
DatabaseConnection.init();
```

La rulare, daca totul este configurat corect:

Connected to database!

## 3. Modelul obiectelor

### Genre

Reprezinta entitatea corespunzatoare tabelei genres.

=> Caracteristici:

- id - identificator unic
- name - numele genului

Clasa foloseste Lombok:

- @Data - genereaza getter/setter/toString
- @NoArgsConstructor
- @AllArgsConstructor

## 4. DAO - Data Access Object

### GenreDAO

Clasa GenreDAO ofera operatii CRUD pentru tabela genres.

=> FUNCTIONALITATE:
``` java
create(String name)
```
- insereaza un gen nou in baza de date
- foloseste PreparedStatement pentru siguranta
- afiseaza confirmarea inserarii

```java
findById(int id)
```
- cauta un gen dupa ID
- returneaza un obiect Genre sau null

```java
FindByName(String name)
```
- cauta un gen dupa nume
- returneaza un obiect Genre sau null

DAO-ul foloseste conexiunea unica furnizata de DatabaseConnection.

## 5. Exemplu de utilizare (din Main.java)

Programul demonstreaza functionalitatea DAO-ului si conexiunea la baza de date.

==> Programul:
- initializeaza conexiunea
- creeaza un obiect GenreDAO
- insereaza un gen nou
- il cauta dupa nume
- il cauta dupa ID

```java
DatabaseConnection.init();

GenreDAO genreDAO = new GenreDAO();

genreDAO.create("Action");

Genre genre1 = genreDAO.findByName("Action");
System.out.println("Found by name: " + genre1);

Genre genre2 = genreDAO.findById(1);
System.out.println("Found by id: " + genre2);
```

==> Output posibil:

Connected to database!

Inserted genre: Action

Found by name: Genre(id=1, name=Action)

Found by id: Genre(id=1, name=Action)

# ~ LABORATORUL 6 -HOMEWORK ~

Aceasta sectiune extinde partea de Compulsory si implementeaza urmatoarele cerinte:
* utilizarea unui connection pool (HikariCP)
* definirea unui model orientat pe obiecte
* implementarea tuturor DAO-urilor necesare
* generarea unui raport HTML folosind FreeMarker
* citirea datelor dintr-un view SQL dedicat raportului

Structura proiectului este organizata in pachete clare: config, dao, model, report, homework.

## 1. Connection Pool - HikariCP

In locul conexiunii Singleton din Compulsory, partea de Homework foloseste un pool de conexiuni pentru performanta si scalabilitate.

### Clasa DataSourceProvider

Aceasta clasa gestioneaza pool-ul HikariCP:
* configureaza URL-ul JDBC, user, parola si driverul MySQL
* seteaza dimenziunea minima/maxima a pool-ului 
* expune metode statice pentru:
	- init() - initializeaza pool-ul
	- getConnection() - ofera o conexiune din pool
	- close() - inchide pool-ul la finalul executiei

Exemplu de initializare:
```java
DataSourceProvider.init();
```

La finalul programului:
```java
DataSourceProvider.close();
```


## 2. Modelul orientat pe obiecte

Modelele sunt implementate in pachetul model si folosesc Lombok 

### Actor

```java
int id;
String name;
```

### Genre

```java
int id;
String name;
```

### Movie

```java
int id;
String title;
LocalDate releaseDate;
int duration;
double score;
int genreId;
```

Aceste clase reprezinta modelul orientat pe obiecte (entitatile din baza de date) si sunt folosite pentru maparea datelor din ResultSet.

## 3. DAO - Data Access Object

Toate DAO-urile folosesc conexiuni din HikariCP si sunt implementate in pachetul dao

### 3.1. GenreDAO

Operatii:
* create(String name)
* findById(int id)
* findByName(String name)

### 3.2. ActorDAO

Operatii:
* create(String name)
* findById(int id)
* findByName(String name)

### 3.3. MovieDAO

Operatii:
* create(Movie movie)
* findById(int id)
* findByName(String title)

Include conversii corecte intre LocalDate si java.sql.Date.

### 3.4. MovieActorDAO

Gestioneaza relatia many-to-many dintre filme si actori

Operatii:
* addActorToMovie(int movieId, int actorId)
* findActorsByMovie(int movieId) -> lista de ID-uri
* findMoviesByActor(int actorId) -> lista de ID-uri

## 4. View SQL pentru raport

Raportul HTML nu citeste direct din tabele, ci dintr-un view numit movie_report, care combina:
* filmele
* genurile
* actorii

Exemplu de structura posibila:
```sql
CREATE VIEW movie_report AS
SELECT
	m.id AS movie_id,
	m.title AS movie_title,
	m.release_date,
	m.duration,
	m.score, 
	g.name AS genre_name
FROM movies m
JOIN genres g ON m.genre_id = g.id;
```

## 5. Generarea raportului HTML - FreeMarker

### Clasa ReportGenerator

Responsabila pentru:
* 1. Citirea datelor din view-ul movie_report
* 2. Construirea unei liste de Map<String, Object> pentru template
* 3. Incarcarea template-ului movies.ftl
* 4. Generarea fisierului report.html

Template-ul este incarcat din: src/main/resources/templates/movies.ftl

### Template-ul movies.ftl

Genereaza un tabel HTML cu:
* ID
* Titlu
* Data lansarii
* Durata
* Scor
* Gen

## 6. Clasa principala - HomeworkMain

Demonstreaza intreg fluxul aplicatiei:
* 1. Initializeaza pool-ul HikariCP
* 2. Creeaza DAO-urile
* 3. Insereaza date de test (genuri, actori, filme)
* 4. Creeaza un film si il leaga de actori
* 5. Genereaza raportul HTML
* 6. Inchide pool-ul

Exemplu de rulare:

HikariCP connection pool initialized!

Inserted genre: Action

Inserted actor: Leonardo DiCaprio

Inserted movie: Inception

Linked movie 1 with actor 1

Linked movie 1 with actor 2

Report generated successfully!

Homework finished successfully!