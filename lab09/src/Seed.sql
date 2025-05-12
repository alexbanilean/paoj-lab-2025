CREATE TABLE Angajati (
    CNP VARCHAR(13) PRIMARY KEY,
    Nume VARCHAR(100),
    Salariu DOUBLE PRECISION
);

CREATE TABLE Proiecte (
    ID SERIAL PRIMARY KEY,
    Denumire VARCHAR(100),
    Buget DOUBLE PRECISION
);

CREATE TABLE Participari (
    CNP_angajat VARCHAR(13),
    ID_proiect INT,
    Rol VARCHAR(50),
    Nr_ore INT,
    PRIMARY KEY (CNP_angajat, ID_proiect),
    FOREIGN KEY (CNP_angajat) REFERENCES Angajati(CNP),
    FOREIGN KEY (ID_proiect) REFERENCES Proiecte(ID)
);
