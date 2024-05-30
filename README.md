# BudgetBuddy

<p align="center">
  <img src="https://github.com/KogelMogel123/BudgetBuddy/assets/19485654/5ff35d5b-7aeb-4526-8187-2e374a689dba" alt="BudgetBuddy">
</p>

## O projekcie
Aplikacja do zarządzania finansami osobistymi na platformie Android, stworzona w Kotlinie. Umożliwia użytkownikom skanowanie paragonów, automatyczne rozpoznawanie wydatków, zarządzanie budżetem, analizę wydatków. Celem projektu jest ułatwienie użytkownikom monitorowania ich wydatków i optymalizacja zarządzania finansami.

> <p align="left">Aplikacja tworzona w ramach <a href="https://100commitow.pl">#100commitow</a>
> <p align="left">Głownym celem jest nauka języka Kotlin oraz środowiska Android Studio

## Wykorzystane Technologie BudgetBuddy
 - Kotlin
 - Android Studio
 - Jetpack Compose
 - Koin
 - Room
 - CameraX
 - Material Design 3
 - ycharts

## Wykorzystane Technologie BudgetBuddyServer
 - C#
 - .NET8
 - Visual Studio 2022

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
 - [x] Komunikacja aplikacji mobilnej z API

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
## Prosty sposób:
  
Wystarczy, że pobierzesz: BudgetBuddy.apk i zainstalujesz na telefonie.

## Postawienie własnego środowiska

Poniżej znajdziesz kroki potrzebne do skonfigurowania i uruchomienia procesu aplikacji BudgetBuddy oraz BudgetBuddyServer. Skonfigurujemy również integrację z Make.com w celu odebrania zdjęcia paragonu i przesłania do ChatGPT.

Krok 1: Rejestracja i konfiguracja konta na [Make.com](https://www.make.com)

Przejdź na Make.com i zarejestruj konto.

Konfiguracja scenariusza:

Utwórz nowy scenariusz.

Dodaj Custom Webhook do odbierania zdjęć i przekazywania zdjęcia

![web1](https://github.com/KogelMogel123/BudgetBuddy/assets/19485654/3da98425-25b2-4d11-8de9-3e18304ae8be)

Dodaj moduł OpenAI z Analyze Image (Vision)

![web2](https://github.com/KogelMogel123/BudgetBuddy/assets/19485654/b471acb2-52d9-4f07-9157-42b5bcd485cc)

Prompt, który opracowałem, aby AI potrafiło zwrócić mi pożądany Response:

Analyze the receipt, identifying each item as a specific type. Use only: “GROCERIES”, “TRANSPORT”, “HOUSING”, “ENTERTAINMENT”, “CLOTHING”, “EDUCATION”, “OTHER”. If a product does not fit into any category then add it to "OTHER."
Then merge all items of the same group and add values. Return an unformatted JSON containing as few characters as possible. The JSON should contain the suggest expense name, category, cost of the grouped products for that category. Cost without currency.
Instructions:
- Answer as honestly as possible.
- Return only JSON
Example:
[{"name": "Example1","cost": "10.00","category": "GROCERIES"},{"name": "Example2","cost": "7.00","category": "CLOTHING"}]

Dodaj stadnardowy moduł Webhook response

![webhook](https://github.com/KogelMogel123/BudgetBuddy/assets/19485654/8092f732-4c06-4f05-9fde-850ca59f0797)

Krok 2: Pobranie i konfiguracja repozytorium

Konfiguracja BudgetBuddyServer:

Otwórz projekt BudgetBuddyServer w Visual Studio 2022.

Otwórz plik appsettings.json i skonfiguruj sekcję AppSettings:

    "AppSettings": {
      "MakeConfiguration": {
        "Url": "<MAKE_COM_ENDPOINT_URL>"
      },
      "ApiUsageSettings": {
        "ApiKeyHeaderName": "ApiKey",
        "UserHeaderName": "User",
        "RequestLimit": 5,
        "BlockDurationMinutes": 60,
        "ValidApiKey": "<YOUR_API_KEY>"
      }
    }

Zbuduj projekt.

Konfiguracja BudgetBuddy:

Otwórz projekt BudgetBuddy w Android Studio.

Otwórz plik build.gradle.kts i skonfiguruj pola buildConfigField:

    defaultConfig {
        buildConfigField(
            "String",
            "API_ENDPOINT",
            "\"<URL_ENDPOINTU_BUDGETBUDDY_SERVER>\""
        )
        buildConfigField(
            "String",
            "API_KEY",
            "\"<TWOJ_KLUCZ_API>\""
        )

Zbuduj projekt.

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
