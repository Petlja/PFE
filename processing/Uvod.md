# Uvod u Processing

*ovde treba da stoji par recenica o tome sta je processing*

Processing može da se skine besplatno sa njihovog [sajta](https://www.processing.org) i nije potrebna instalacija.

## Crtanje

Grafika u oblasti programiranja je često tema koja se ne pominje u školama.
Mnogi programski jezici ne podržavaju jednostavno pravljenje programa sa vizuelnim elementima, već zahtevaju veliko predznanje.
Processing rešava mnoge probleme time što ima unapred spremne funkcije za crtanje jednostavnih oblika i njihovu manipulaciju.

Na slici ispod je prikazan prozor koji se pojavi pri pokretanju aplikacije processing.
Pokretanjem praznog program (dugme) pojavljuje se prazan prostor po kome može da se crta.

![pic1]("Izgled Processing aplikacije")

### size

Prozor je dosta mali (100px x 100px) tako da prva stvar koja se radi jeste menjanje veličine.
To se radi pozivanjem funkcije `size(w, h)`, kojoj se prosledjuje željena sirina (`width`) i visina (`width`) prozora.

```Java
size(800, 600);
```

### background

Najjednostavnija manipulacija dobijenog prozora jeste menjanje boje pozadine.
To se radi pomoću finkcije `background(r, g, b)` kojoj se prosleđuje RGB vrednosti (udeo crvene, zelene i plave boje).

```Java
background(200, 240, 255)
```
Menjanjem parametara se menja i boja

### ellipse

Za crtanje osnovnih geometrijskih oblika (elipse, pravougaonici, linije...) postoje posebne funkcije.
Recimo, za krug se koristi funkcija `ellipse(x, y, w, h)` kojoj se prosleđuje koordinate centra i dva prečnika.

**Napomena** Elipsa sa jednakim prečnicima je krug.

```Java
ellipse(300, 200, 100, 100);
```

**Oprez** Processing koristi koordinatnu osu koja je okrenuta po y osi, tako da y koordinata pretstavlja udaljenost od gornje ivice, a ne donje.

### fill i stroke

Geometriski oblici su sačinjeni od dva dela, unutračnjosti i ivice.
Menjanje boja tih delova se vrši funkcijama `fill(r, g, b)` i `stroke(r, g, b)`.

```Java
fill(255, 240, 200);
stroke(100, 40, 20);
```

### Reference

Pored navedenih funkcija postoje mnogobrojne druge i nije potrebno znati ih sve napamet.
Korišćenje referenci je dobra praksa jer koncetriše razmišljanje na logiku.
Reference možete naći u help->references ili na [njihovom sajtu](https://processing.org/).

## Simulacije

Najveća moć processinga je u njegovoj mogućnosti da na osnovu neke logike menja slike.
Time može da se koristi za pravljenje raznih simulacija, animacija i igrica.
 
### setup() i draw()

Funkcije koje ovo omogućavaju su `setup()` i `draw()`.
`setup()` je funkcija koja se pokreće jedanput na početku pokretanja programa i služi za podešavanje 
veličine ekrana i početnih vrednosti promenljivih.
Dok, sa druge strane, `draw()` je funkcija koja se izvršava više puta (najčešće 30 puta u sekundi) i služi 
da se u njoj menjaju vrednosti promenljivih i za crtanje.

Sledeći program je prier korišćenja funkcija:

```Java
void setup() {
 size(500, 500);
}

void draw() {
 background(200, 240, 255);
 fill(255, 240, 200);
 ellipse(300, 200, 100, 100);
}
```

### Pomeranje

Prethodni program je vršio iscrtavanje iste slike 30 puta u sekundi što nije korisno.
Da bi se krug pomerao, potrebno je uvesti promenljive za njegove koordinate.
Neka krug ima samo `x` kordinatu koja će biti tipa `float` i neka se menja za `1.0` 30 puta u sekundi:

```Java
float x;

void setup() {
 x = 100.0;
 size(500, 500);
}

void draw() {
 x += 1.0;

 background(200, 240, 255);
 fill(255, 240, 200);
 ellipse(x, 250, 100, 100);
}
```

**Napomena** Deklarisanje globalnih promenljivih se vrši van funkcija `setup()` i `draw()`, a inicializacija u 
funkciji `setup()`.

### Interakcije

Pored samog menjanja vrednosti promenljivih, moguće je napraviti program koji uzima komande od korisnika.
Postoje dve najbitnije funkcije: `mousePressed()` i `keyPressed()`.
Dodavanjem sledećeg primera na prethodni kod, loptica se pomera ukoliko je pritisnuto određeno dugme:

```Java
void mousePressed(){
 if (mouseKey == LEFT) x = 100;
 if (mouseKey == RIGHT) x = 200;
}

void keyPresed() {
 if (key == 'a') x = 100;
 if (key == 'd') x = 200;
}
````

## Bouncy Ball

### Modelovanje

### Odbijanje od zidova

### Merenje i primena

## Projekti

### Flood fill

### Matematičko klatno

### Električno polje

### N body

### 4 mrava

### Kosi hitac

### Game of Life

### Crtanje funkcija

### Tetris bot
