# Uvod u Processing

Grafika u oblasti programiranja je često tema koja se ne pominje u školama.
Mnogi programski jezici ne podržavaju jednostavno pravljenje programa sa vizuelnim elementima, već zahtevaju veliko predznanje.
Processing rešava mnoge probleme time što ima unapred spremne funkcije za crtanje jednostavnih oblika i njihovu manipulaciju.

Processing može da se skine besplatno sa njihovog [sajta](https://www.processing.org) i nije potrebna instalacija.
Na sajtu takđe postoje dobre reference i tutorijali na engleskom.

## Crtanje

Na slici ispod je prikazan prozor koji se pojavi pri pokretanju aplikacije processing.
Pokretanjem praznog program (dugme) pojavljuje se prazan prostor po kome može da se crta.

![pic1](https://processing.org/tutorials/gettingstarted/imgs/Fig_02_01.gif "Izgled Processing aplikacije")

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

**Napomena** Processing koristi koordinatnu osu koja je okrenuta po y osi, tako da y koordinata pretstavlja udaljenost od gornje ivice, a ne donje.

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
```

## Bouncy Ball

Do sada su prikazane osnovne funkcionalnosti Processinga, ovaj odeljak se bavi konkretnim primerom.
Bouncy Ball je simulacija kretanja loptice pod dejstvom zemljine teže.

### Modelovanje

Bouncy Ball je fizički sistem koji je potrebno pretvoriti u matematički model (jednačine) da bi mogao da se simulira.
U školama se uči poznata jednačina vx = Δx / Δt, koja kaže da brzina tela pretstavlja količnik promene položaja (Δx) i vremena za koje se ta promena dogodi (Δt).

U simulaciji se promena promenljivih dešava 30 puta u sekundi (`draw()`), pa se stavlja Δt = 1/30.
Ako je poznata brzina tela, onda je Δx = vx * Δt, odnosno pri svakom izvrsavanju funkcije `draw()` vrednost koordinate
se menja za Δx: `x = x + vx * dt`.
Gotovo identične jednačine važe i za ubrzanje: ax = Δvx / Δt, `vx = vx + ax * dt`.

```Java
float x, y, vx, vy, g, dt, r, scale;

void setup() {
  size(800, 600);
  x = 1; // x koordinata
  y = 1; // y koordinata
  vx = 1; // brzina po x osi
  vy = 0; // brzina po y osi
  g = 9.81;  // Ubrzanje zemljine teze
  dt = 1/30.0; // Promena vremena
  r = 0.1; // poluprecnik loptice
  scale = 100;
}

void draw() {
  vy += g*dt;
  x += vx*dt;
  y += vy*dt;

  background(200, 240, 255);
  fill(255, 240, 200);
  ellipse(x * scale, y * scale, 2 * r * scale, 2 * r * scale);
}
```

**Napomena** Pored implementacije samog modela, potreban je način da se simulacija prikaže na lep način.
Vrednosti koje se čuvaju u promenljivama za koordinate su u metrima, a Processing zahteva da jedinice budu u pikselima.
Ovaj problem se rešava uvodjenjem promenljive `scale` koja pretstavlja broj piksela koji pretstavljaju jedan metar.
Množenjem promenljivih ovim brojem pre iscrtavanja dobijaju se vrednosti u pikselima.

### Odbijanje

U prethodnom programu loptica je padala i izvan ekrana i više se nije pojavljivala.
Kod za odbijanje od podloge se može implentirati nakon koda za promenu koordinata:

```Java
y += vy*dt;
x += vx*dt;
if ((y + r)*scale > height) {
  y -= vy*dt;
  x -= vx*dt;
  vy = -vy;
}
```

**Napomena** Da bi osigurali da loptica ne ode ispod ekana, potrebno je da čim loptica ode ispod vrati u položaj u kome je 
bila pre udarca u pod i obrne smer brzine.

Odbijanje od levog i desnog zida se radi identično:

```Java
if ((x + r)*scale > height && (x - r)*scale < 0) {
  y -= vy*dt;
  x -= vx*dt;
  vx = -vx;
}
```

### Merenje i primena

Osim što lepo izgleda, simulacija treba da vrati korisnu informaciju.
Najjednostavnije vrednosti koje mogu da se dobiju iz prethodne simulacije su energije loptice (kinetička i potencijalna).

Formula za kinetičku, potencijalnu i ukupnu energiju su: `Ek = m*v*v/2`, `Ep = m*g*h` i `Eu = Ek + Ep`.
Te vrednosti se mogu jednostavno ispisati na ekran:

```Java
float v = sqrt(vx * vx + vy * vy);
float Ek = m*v*v/2;
float Ep = m*g*(height/s - y);
float Eu = Ek + Ep;
text("Брзина: " + nf(v, 0, 2) + " m/s", 5, 20);
text("Кинетичка Енергија: " + nf(Ek, 0, 2) + " J", 5, 40);
text("Потенцијална Енергија: " + nf(Ep, 0, 2) + " J", 5, 60);
text("Укупна Енергија: " + nf(Eu, 0, 2) + " J", 5, 80);
```

**Napomena** Funkcija `nf(float, int, int)` formatira realan broj.
Prvi argument je broj koji se formatira, drugi pretstavlja broj cifara pre decimalne tačke (0 označava proizvoljan broj cifara), a treći nakon.

## Projekti

Na zimskom seminaru Primenjene fizike i elektronike su podeljeni projekti za samostalni rad.
U ovom odeljku su opisani ciljevi projekata.

### Kosi hitac

1. Na početku postoji loptica koja se nalazi na podu u jednom od ćoškova.
Pritiskom na ekran, loptica dobija početnu brzinu usmerenu od loptice ka mišu.

2. Pre ispaljivanja loptice potrebno je prikazati putanju kojom će se loptica kretati nakon pritiska i ispisati domet.

3. Ubaciti u simulaciju da pre ispaljivanja može da se namesti smer i brzina vetra.

### 4 mrava

1. Postoje 4 mrava koji su postavljeni na po jednom od 4 temena kvadrata.
Svaki mrav se kreće ka mravu koji se nalazi levo od njega.
Simulirati ovo ponašanje. Izlaz treba izgledati kao 4 spirale.

2. Napraviti da postoji `n` mrava u temenima pravilnog n-tougla.

3. Postaviti početne položaje mrava u nasumične koordinate.

### N body

1. U posudi se nalazi `n` čestica i svaka se kreće istom brzinom `v`.
Čestice su okruglog oblika i odbijaju se od zidova.

2. Implementirati odbijanje čestica.

3. Dodati na vrh posude klip i ispisati pritisak koji deluje na klip.

### Matematičko klatno

1. Simulirati matematičko koje osciluje pod dejstvom zemljine teže i ispisati njegov period.

2. Simulirati dvostruko mateatičko klatno sa istim uslovima.

3. Simulirati i iscrtati dva dvostruka klatna čiji se početni uglovi razlikuju za veoma malu vrednost.

### Crtanje funkcija

1. Nacrtati koordinatne ose i grafik proizvoljne funkcije.

2. U isto vreme nacrtati izvod i integral funkcije.

3. Nacrtati tangentu na funkciju na koordinati x na kojoj se nalazi miš. 
Takođe, nacrtati površinu ispod grafika od tačke x=0 do koordinate x na kojoj se nalazi miš.
Ispisati koeficijent prave tangente i površinu.

### Električno polje

1. Pritiskom na ekran dodati naelektrisane čestice (pozitivne xili negativne) i iscrtati intenzitet električnog polja 
u svakoj tački na ekranu.

2. Iscrtati liniju polja i ekvipotencijalu koja prolazi kroz miš.

3. Iscrtati vektore električnog polja na velikom broju tačaka.

### Flood fill

1. Data je matrica `n*m` cijim poljima je dodeljena boja. Simulacija se pokreće pritiskom na jedno od polja matrice 
(boja pritisnutog polja je `c`). Treba promeniti boju svih polja koje imaju boju `c` i imaju direktan put do pritisnutog polja
preko bilo kog polja sa bojom `c`.

2. Prikazati simulaciju usporeno tako da jedno polje menja boju po pozivanju funkcije `draw()`.

3. Ispisati udaljenost svakog novo obojenog polja od pritisnutog polja.

### Game of Life

1. Napraviti simulaciju Convejove igre zivota (Conway's Game of Life): [pravila](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

2. Dodati dodatne opcije za promenu pravila, odnosno dugme za promenu potrebnih suseda za promenu stanja.

3. Napraviti program kojem se prosledjuje tekstualni fajl sa pravilima i koji radi sa `n` stanja.

### Tetris bot

Napraviti robota koji igra tetris.
