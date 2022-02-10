INSERT INTO COMMENT (DESCRIPTION, DATE_ADDED, ADDED_BY) VALUES
('Proszę o dodanie logów do błędu.', '2021-12-11', 'Ula Tester'),
('Złoszenie niezasadne, prośba o analizę z dokumentacją HLD.', '2021-11-03', 'Michał Kowalski'),
('Retest pozytywny - zamykam zgłoszenie.', '2021-04-22', 'Janusz Polak');

INSERT INTO DEFECT (TITLE, REPORTED_DATE, LAST_UPDATE_DATE, COMPONENT, ENVIRONMENT, DEFECT_DESCRIPTION, EXPECTED_RESULT, PRIORITY) VALUES
('Błąd przy logowaniu do aplikacji', '2021-10-02', '2021-12-01', 'Aplikacja bankowa', 'T22', 'Po wpisaniu nazwy uzytkownika oraz hasła klikamy na przycisk ZALOGUJ - pojawia się błąd połączenia z serwerem.',
'Po wpisaniu nazwy użytkownika oraz hasła klikamy na przycisk ZALOGUJ - poprawne zalogowanie do aplikacji.', 'CRITICAL'),
('Przycisk inicjujący założenie rachunku - nieaktywny', '2021-11-23', '2021-12-01', 'Aplikacja bankowa', 'T34',
'Po zalogowaniu do aplikacji bankowej, wejście w zakładkę KONTO z górnego menu. Przejście na ekran z informacjami na temat rachunku. Użytkownik nie posiada jeszcze aktywnego rachunku. Kliknięcie na przycisk ZAŁÓŻ RACHUNEK. Przycisk nieaktywny',
'Dla nowego klienta w zakładce KONTO powinna być dostępna opcja założenia rachunku - aktywny przycisk ZAŁÓŻ RACHUNEK', 'CRITICAL');

INSERT INTO PROJECT (PROJECT_SIGNATURE, PROJECT_NAME, DESCRIPTION) VALUES
('P1122', 'Konto biznes - nowy klient', NULL),
('P0999', 'Aplikacja mobilna - proces zakładani konta', NULL);

INSERT INTO TEST_CASE (TITLE, DESCRIPTION, CREATED_BY, PRIORITY, SOURCE, STATUS) VALUES
('Logowanie do aplikacji', 'Celem testu jest sprawdzenie funkcjonalności logowania do aplikacji bankowej przez użytkownika.', 'Ula Tester', 'CRITICAL', 'Dokument HLD Konto biznes', 'PASS'),
('Werifikacja biometryczna', 'Celem testu jest sprawdzenie funkcjonalności weryfikacji biometrycznej - skan dowdu i skan twarzy uzytkownika.', 'Ula Tester', 'CRITICAL', 'HLD usługi biometrii', 'NOT_STARTED');

INSERT INTO TEST_SCENARIO (TITLE) VALUES ('Testy manualne ekranu logowania i rejestracji'),
('Testy usług zapisujących klienta w bazie PERSON_IDENT');

INSERT INTO TEST_STEP (STEP_NR, STEP_ACTIONS, EXPECTED_RESULTS, TESTER_COMMENT, STATUS) VALUES
('1', 'Wpisz w polu username login a w polu password hasło użytkownika. Kliknij na przycisk ZALOGUJ', 'Nastąpiło poprawne zalogowanie. Widok strony głównej aplikacji.', 'login: KasiaBorek; hasło: kasiunia1234', 'NOT_STARTED'),
('2', 'Na ekranie strony głównej w prawym górnym rogu menu, kliknij na strzałkę obok zębatki.', 'Nastąpiło rozwinięcie opcji, a dostępne powinny być: Wyloguj, Zmień dane, Skontaktuje się z doradcą',
NULL, 'NOT_STARTED');


