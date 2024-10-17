-- Creating T_DOCUMENTS TABLE
CREATE TABLE T_DOCUMENTS (
    DOC_CODE INT PRIMARY KEY NOT NULL,
    DOC_FILE_PATH VARCHAR(255) NOT NULL,
    DOC_DESCRIPTION VARCHAR(255),
    DOC_CREATED_AT DATETIME,
    DOC_UPDATED_AT DATETIME,
    TDC_CODE INT NOT NULL, 
    ETU_CODE VARCHAR NOT NULL,
    CONSTRAINT FK_TDC_CODE FOREIGN KEY (TDC_CODE) REFERENCES T_TYPE_DOCUMENT(TDC_CODE),
    CONSTRAINT FK_ETU_CODE FOREIGN KEY (ETU_CODE) REFERENCES T_ETUDIANT(ETU_CODE)
);


-- creating Annee Academique table 
CREATE TABLE T_ANNEE_ACADEMIQUE (
    AAC_CODE INT PRIMARY KEY NOT NULL,
    AAC_LIB VARCHAR(255) NOT NULL,
    AAC_CREER_PAR VARCHAR(255) NOT NULL,
    AAC_MODIFIER_PAR VARCHAR(255),
    AAC_DATE_CREATION DATETIME,
    AAC_VERSION INT
);

-- creating T_FILIERE table
CREATE TABLE T_FILIERE (
    FIL_CODE INT PRIMARY KEY NOT NULL,
    FIL_LIB VARCHAR(255) NOT NULL,
    FIL_CREER_PAR VARCHAR(255) NOT NULL,
    FIL_MODIFIER_PAR VARCHAR(255),
    FIL_DATE_CREATION DATETIME,
    FIL_VERSION INT
)

-- creating T_NIVEAU table
CREATE TABLE T_NIVEAU (
    NIV_CODE INT PRIMARY KEY NOT NULL,
    NIV_LIB VARCHAR(255) NOT NULL,
    NIV_CREER_PAR VARCHAR(255) NOT NULL,
    NIV_MODIFIER_PAR VARCHAR(255),
    NIV_DATE_CREATION DATETIME,
    NIV_VERSION INT
);

-- creating T_MATIERE table
CREATE TABLE T_MATIERE (
    MAT_CODE INT PRIMARY KEY NOT NULL,
    MAT_LIB VARCHAR(255) NOT NULL,
    MAT_CREER_PAR VARCHAR(255) NOT NULL,
    MAT_MODIFIER_PAR VARCHAR(255),
    MAT_DATE_CREATION VARCHAR(100),
    MAT_VERSION INT
);

-- creating T_CLASSE table
CREATE TABLE T_CLASSE (
    CLA_CODE INT PRIMARY KEY NOT NULL,
    CLA_LIB VARCHAR(100) NOT NULL,
    CLA_CREER_PAR VARCHAR(255) NOT NULL,
    CLA_MODIFIER_PAR VARCHAR(255),
    CLA_DATE_CREATION VARCHAR(50),
    CLA_VERSION INT,
    NIV_CODE INT,
    FIL_CODE INT
);

-- creating T_RUBRIQUE table
CREATE TABLE T_RUBRIQUE (
    RUB_CODE INT PRIMARY KEY NOT NULL,
    RUB_LIB VARCHAR(100) NOT NULL,
    RUB_MONTANT DECIMAL(10, 2) NOT NULL,
    RUB_FRAIS_UNIQUE BIT NOT NULL,
    RUB_CREER_PAR VARCHAR(255) NOT NULL,
    RUBV_MODIFIER_PAR VARCHAR(255),
    RUB_DATE_CREATION VARCHAR(50),
    RUB_VERSION INT,
);
