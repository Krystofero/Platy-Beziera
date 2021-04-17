Program wykorzystuje współrzędne punktów kluczowych czajnika, łyżki ,filiżanki z Utah podane
w trzech plikach .txt (teacup1.txt , teapot1.txt, teaspoon1.txt) 
aby wyznaczyć współrzędne pozwalające zbudować przedmiot z płatów Bezier'a 
(tworzone są trzy pliki: teacup1bezier.txt, teapot1bezier.txt, teaspoon1bezier.txt). 

Efekty można sprawdzić np. za pomocą programu ParaView otwierając w nim utworzone pliki
(należy odznaczyć opcję "Have Headers" a następnie dodać filtr "Table to Points").

Aby uruchomić program należy skopiować cały folder "PlatyBezier" i dodać go do projektu w kompilatorze Javy(program był testowany w IntelliJ IDEA). 
Aby poprawnie działał należy zmienić sześć ścieżek do plików(sicezka1, sciezka2, sciezka3, ...) na zgodne z położeniem plików na naszym dysku. 


Konstruktor bezargumentowy 3 razy wywołuje funkcję "Bezier" podając w niej za pierwszy argument ścieżkę do istniejącego pliku zawierajcego punkty kluczowe , 
natomiast jako drugi argument ścieżkę do pliku jaki ma zostać utworzony jako wynik działania programu.
Trójwymiarowa tablica k przechowuje wszystkie płaty i ich punkty(o współrzędnych X, Y, Z).
Funkcje pomocnicze; "dwomianNewtona"  oraz "bBersteina" są potrzebne do zaimplementowania wzorów na współrzędne X, Y, Z  w pliku wyjściowym.

W funkcji "Bezier" na początku odczytuję z pliku wejściowego linia po linii wszystkie zmienne X, Y, Z i wpisuję je do tablicy k.
Nastepnie obliczam punkty PX2 , PY2, PZ3 (czyli wyjściowe X,Y,Z) które zapisuję do pliku wyjściowego.
W mainie wywołuję utworzony wcześniej bezargumentowy kontruktor.
