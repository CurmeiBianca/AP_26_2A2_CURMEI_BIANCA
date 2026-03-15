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
- getName() - numele profilului
- getImportance() - importanta (numarul de relatii)
- getRelationships() - relatiile profilului
	Aceasta interfata permite tratarea uniform a persoanelor si companiilor.

### 2. Person (abstract)

Clasa de baza pentru toate tipurile de persoane.

* PROPRIETATI:
- name - numele persoanei
- birthdate - data nasterii
- relationships - Map<Profile, String> cu relatiile persoanei

* FUNCTIONALITATE:
- addRelationship(Profile, String) - adauga o relatie
- getImportance() - numarul de relatii
- toString() - afiseaza numele, data nasterii si importanta

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

- addRelationship(Profile, String) - adauga o relatie
- getImportance() - numarul de relatii
- toString() - afiseaza numele, industria si importanta

### 6. SocialNetwork

Clasa care gestioneaza intreaga retea sociala.

* FUNCTIONALITATE:

- addProfile(Profile) - adauga un profil in retea
- printNetwork() - afiseaza profilele ordonate descrescator dupa importanta
- buildGraph() - construieste un graf neorientat al relatiilor dintre profile

Graful returnat este folosit ulterior in partea de Advanced pentru analiza conectivitatii.

### 7. Exemplu de utilizare (din Main.java)

Programul creeaza persoane, companii si relatii, apoi afiseaza reteaua:

SocialNetwork network = new SocialNetwork();
network.addProfile(p1);
network.addProfile(p2);
network.addProfile(d1);
network.addProfile(c1);
network.addProfile(c2);

network.printNetwork();

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

1) Identificarea Punctelor de Articulatie
Metoda: public <List<Profile> findArticulationPoints()
Un punct de articulatie este un nod a carui eliminare creste numarul de componente conexe ale grafului. Intr-o retea sociala, aceste noduri sunt entitati critice care conecteaza grupuri altfel separate.
* ALGORITMUL FOLOSESTE:
I. discoveryTime - momentul descoperirii nodului in DFS
II. low - cel mai mic discovery time accesibil prin muchii de intoarcere
III. parent - parintele in arborele DFS
IV. articulationPoints - setul final de noduri critice
* CONDITIILE TARJAN IMPLEMENTATE:
I. radacina DFS cu >= 2 copii
II. low[v] >= discoveryTime[u] pentru un copil v
Daca oricare dintre conditii este indeplinita, nodul este marcat ca punct de articulatie.
       
2) Determinarea Componentelor Biconexe
Metoda: public List<List<Profile>> findBiconnectedComponents()
O componenta biconexa este o submultime de noduri care ramane conexa chiar daca eliminam orice nod din ea. Aceste componente reprezinta zone stabile ale retelei.

## ALGORITMUL FOLOSESTE:
I. aceleasi structuri discoveryTime, low, parent
II. o stiva de muchii (edgeStack) pentru a extrage componentele
* MECANISMUL:
Cand algoritmul detecteaza o separare (low[v] >= discoveryTime[u]), extrage din stiva toate muchiile pana la (u, v) si formeaza o componenta biconexa.
       
## Teste Junit
Fisierul SocialNetworkTest.java valideaza corectitudinea algoritmilor.
1) Testarea punctelor de articulatie
Testul confirma ca:
I. Alice, TechCorp si Bob sunt puncte de articulatie
II. Charlie si DesignHub nu sunt
Acest lucru reflecta structura reala a grafului.

2) Testarea componentelor biconexe
Testul verifica:
I. fiecare componenta are cel putin doua noduri
II. suma nodurilor din componente >= numarul total de profile
Aceste conditii sunt corecte pentru un graf neorientat cu multiple componente biconexe.



# ~ LABORATORUL 4 - COMPULSORY ~

Aceasta sectiune implementeaza un model simplu al unui oras, in care:
* intersectiile sunt noduri ale grafului,
* strazile sunt muchii cu nume si lungimi, 
* iar scopul este pregatirea datelor pentru un algoritm de tip Minimum Spanning Tree (MST), necesar pentru instalarea cablurilor de supraveghere
Cerinta este rezolvata folosind colectii Java adecvate si Java Stream API.

1) Modelul obiectelor
* Intersection
Reprezinta o intersectie din oras.
==> CARACTERISTICI:
I. are un singur atribut: name
II. este comparabila (Comparable<Intersection>)
III. este hashable (prin @EqualsAndHashCode)
IV. este afisabila (@ToString)
Acest lucru permite:
I. sortarea intersectiilor
II. stocarea lor intr-un HashSet fara duplicate

* Street
Reprezinta o strada care conecteaza doua intersectii.
==> CARACTERISTICI:
I. name - numele strazii
II. length - lungimea strazii
III. firstIntersection, secondIntersection - capetele strazii
IV. este comparabila (Comparable<Street>) dupa lungime
Acest lucru permite sortarea strazilor intr-o LinkedList.
2) Generarea intersectiilor cu Stream API
Intersectiile sunt generate folosind Java Stream API:
List<Intersection> intersections = 
	IntStream.rangeClosed(1, 10)
		.mapToObj(i -> new Intersection("I" + i))
		.toList();

       Aceasta demonstreaza:
* folosirea IntStream
* generarea obiectelor cu mapToObj
* colectarea intr-o lista

3) Crearea si Sortarea Strazilor
Strazile sunt stocate intr-o: LinkedList<Street>
Sortarea se face cu un comparator lambda: 
streets.sort((s1, s2) -> Integer.compare(s1.getLength(), s2.getLength()));
Aceasta respecta cerinta de a folosi:
* LinkedList
* un comparator exprimat ca lambda
* obiecte comparabile

4) Set de intersectii - Eliminarea duplicatelor
Pentru a demonstra proprietatile unui Set, se foloseste:
Set<Intersection> intersectionSet = new HashSet<>(intersections);
intersectionSet.add(new Intersection("I1"));
Datorita implementarii equals() si hashCode() in Intersection, obiectul duplicat nu este adaugat.

5) Exemplu de rulare (din Main.java)
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
