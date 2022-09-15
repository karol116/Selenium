# Selenium
Adres testowanej aplikacji:
https://ta-ebookrental-fe.herokuapp.com

Przygotowane klasy odpowiadające wszystkim podstronom aplikacji webowej:
https://github.com/karol116/Selenium-Test-Project/tree/master/Selenium-Tests/src/main/java/pages

Testy napisane za pomocą Selenium WebDriver:
https://github.com/karol116/Selenium-Test-Project/tree/master/Selenium-Tests/src/test/java

Przygotowane przypadki testowe wykorzystane w testach napisanych za pomocą Selenium w Javie:
https://github.com/karol116/Selenium/blob/master/PrzypadkiTestoweProjektKo%C5%84cowy.xlsx

# Specyfikacja:

Tworzona aplikacja ma za zadanie realizować następującą funkcjonalność:

Ekran nr 1 – logowanie/rejestracja nowego użytkownika

Pozostałe ekrany dostępne są po zalogowaniu.

Ekran nr 2 – lista tytułów.

wyświetlona lista: autor, tytuł, rok wydania
możliwość dodawania, edycji, usuwania, wejścia w listę egzemplarzy
Ekran nr 3 – lista egzemplarzy

wyświetlona lista: data zakupu, status (na stanie, wypożyczona)
możliwość dodawania egzemplarzy, edycji, usuwania, wejścia w listę wypożyczeń

Ekran nr 4 – lista wypożyczeń

wyświetlona lista: imię i nazwisko klienta, data wypożyczenia, data wygaśnięcia
możliwość wprowadzenia i usunięcia wypożyczenia
możliwość edycji

Ekran nr 5 – wypożyczenie

formatka z polami: imię i nazwisko klienta (1 pole), data wypożyczenia, data wygaśnięcia
przy wejściu jako nowe wypożyczenie dostępne do edycji dwa pierwsze pola (drugie ustawia się domyślnie na sysdate()+3, pierwsze na sysdate())
przy wejściu jako edycja dostępne do edycji wszystkie pola.
Każdy użytkownik pracuje niezależnie na swojej liście eBooków – jest to realizowane przez backend.

Adres dla testów za pomocą narzędzia Postman (Backend):
https://ta-ebookrental-be.herokuapp.com

Kontrolery:
https://github.com/kodilla/ta-ebooklibrary-backend/tree/master/src/main/java/com/kodilla/ebooklibrary/controller
