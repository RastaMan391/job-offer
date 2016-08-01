# job-offer

# 1. SQL #
Zaprojektować uproszczony model, który pozwoli obsłużyć sytuację: konto bankowe klienta, operacja na koncie będąca uznaniem lub obciążeniem. 
Reguły:
- Klient może posiadać więcej kont
Należy zapisać jakie wyodrębniłeś tabele i jakie są między nimi powiązania, oraz zapisać zapytanie SQL, które:

a) Wyciągnie klientów powyżej 30 lat, którzy nie mieli żadnych obrotów (wpłat/wypłat) w styczniu 2013. 

b) Wyciągnie imiona i nazwiska osób, które w I kwartale 2013r. posiadały sumę wpłat przekraczającą 100 tyś. zł.

c) Wyciagnie użytkownika o największych obrotach w roku 2012


# 2. Samochod #
Mamy klasę Samochod o atrybutach 

- String: marka 
- String: model
- String: pojemność (trzymana zawsze w postaci X.X, gdzie X to znak 0-9)

oraz metodę

Collection<Samochod> function(Samochod[] cars)

Należy napisać ciało metody (dowolny język), która na wyjściu zwróci kolekcję elementów jednocześnie eliminując duplikaty z tablicy wejściowej oraz sortując po marce, modelu, pojemnosci (zachowując kolejność atrybutów przy sortowaniu).



# 3. Poker #
Zadanie z nutką hazardową - Poker. Masz metodę, która dostaje konfigurację pięciu kart i ma sygnaturę
? check (? cards)
Zadaniem metody jest sprawdzenie czy w zestawie kart występuje STRIT lub KARETA. Każda pozostała konfiguracja nas nie interesuje. Należy wymyślić w jaki sposób:

* reprezentowana będzie karta, 

* metoda zwróci wynik (czy będzie to kod liczbowy, czy cokolwiek innego)

* w jaki sposób dostanie zbiór kart wejściowych (tablica, kolekcja). 

Można tworzyć dowolne typy oraz metody pomocnicze

Do zadań 2 i 3 należy napisać Unit Testy.
