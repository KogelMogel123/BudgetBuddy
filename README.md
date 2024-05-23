# BudgetBuddy

<p align="center">
  <img src="https://github.com/KogelMogel123/BudgetBuddy/assets/19485654/5ff35d5b-7aeb-4526-8187-2e374a689dba" alt="BudgetBuddy">
</p>

## O projekcie
Aplikacja do zarządzania finansami osobistymi na platformie Android, stworzona w Kotlinie. Umożliwia użytkownikom skanowanie paragonów, automatyczne rozpoznawanie wydatków, zarządzanie budżetem, analizę wydatków. Celem projektu jest ułatwienie użytkownikom monitorowania ich wydatków i optymalizacja zarządzania finansami.

> <p align="left">Aplikacja tworzona w ramach <a href="https://100commitow.pl">#100commitow</a>
> <p align="left">Głownym celem jest nauka języka Kotlin oraz środowiska Android Studio

## Wykorzystane Technologie
 - Kotlin
 - Android Studio
 - Jetpack Compose
 - Koin
 - Room
 - CameraX
 - Material Design 3
 - ycharts

## MUST HAVE
 - [x] Podstawowy interfejs użytkownika
 - [x] Możliwość wpisania zakupów bez paragonu
 - [x] Możliwość edycji/usuwania wydatków
 - [x] Robienie zdjęcia z poziomu aplikacji
 - [x] Kategoryzacja zakupów
 - [x] Rozpoznawanie Paragonów (Bezpośrednia komunikacja z serwisem AI, póki nie ma pośrednika w postaci API)
 - [x] Zapis przetworzonego paragonu na wpisy wydatków do bazy
 - [x] Lokalna baza danych

## SHOULD HAVE
 - [x] Możliwość wybierania zdjęć paragonów z galerii
 - [x] Możliwość ustawiania budżetu na aktualny miesiąc
 - [x] Statystyki wydatków (Wykres kołowy)
 - [x] Tłumaczenie interfejsu angielski/polski w zależności od systemu użytkownika

## COULD HAVE
 - [x] Napisanie API w .NET 8 do komunikacji pomiędzy aplikacją mobilną, a serwisem z AI
 - [x] Walidacja pliku z paragonem (wielkość pliku, typ pliku)
 - [x] Postawienie publicznego serwera z API
 - [x] Zabezpieczenie przed nadmiarowym użyciem serwisu AI
 - [ ] Komunikacja aplikacji mobilnej z API

## Architektura

![arch](https://github.com/KogelMogel123/BudgetBuddy/assets/19485654/860848ee-319b-4531-8c00-84887a83d202)

## Wygląd aplikacji
Ikona aplikacji

![Screenshot_20240518_144159_One UI Home](https://github.com/KogelMogel123/BudgetBuddy/assets/19485654/fb9bc326-6a35-40ea-b7e8-d32231364244)

Menu

![Screenshot_20240518_142353_Budget Buddy](https://github.com/KogelMogel123/BudgetBuddy/assets/19485654/74e188cf-6c36-4a6f-babb-dd4b0a6419d9)

Pulpit

![Screenshot_20240518_141353_Budget Buddy](https://github.com/KogelMogel123/BudgetBuddy/assets/19485654/52436ab5-c08a-4bcc-9443-167810b3bd3a)

Wydatki

![Screenshot_20240518_141406_Budget Buddy](https://github.com/KogelMogel123/BudgetBuddy/assets/19485654/c7c4839b-a3b5-4ba9-80c1-2b43afbc8dce)

Skanowanie paragonu

![Screenshot_20240518_141911_Budget Buddy](https://github.com/KogelMogel123/BudgetBuddy/assets/19485654/1d70d89b-d77c-4718-95fa-dd6bb5196f1c)

## Jak uruchomić aplikację
TODO

## Plany na przyszłość
 - Bezpośrednia komunikacja API z OpenAI
 - Baza danych
 - Logowanie
 - Rejestracja
 - Ergonomiczny interfejs użytkownika
 - Personalizacja interfejsu użytkownika
 - Statystyki cen produktów w czasie
 - Tworzenie grup
 - Zapraszanie innych użytkowników (np. rodzina, partner/ka)
 - Współdzielenie paragonów/wydatków z innymi użytkownikami w ramach grup
 - Rozpoznawanie wzorców w wydatkach użytkownika związanych z porami roku czy świętami i dostarczanie przewidywań budżetowych
 - Notyfikacje dotyczące osiągnięcia granicy budżetu finansowych
