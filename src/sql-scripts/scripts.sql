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

-- creating T_PROMOTION table
CREATE TABLE T_PROMOTION (
    PRO_CODE INT PRIMARY KEY NOT NULL,
    PRO_LIB VARCHAR(255) NOT NULL,
    PRO_CREER_PAR VARCHAR(255) NOT NULL,
    PRO_MODIFIER_PAR VARCHAR(255),
    PRO_DATE_CREATION VARCHAR(50),
    PRO_VERSION INT NOT NULL,
    AAC_CODE INT NOT NULL,
    FOREIGN KEY (AAC_CODE) REFERENCES T_ANNEE_ACADEMIQUE(AAC_CODE) ON DELETE CASCADE
);

-- creating T_CLASSE table
CREATE TABLE T_CLASSE (
    CLA_CODE INT PRIMARY KEY NOT NULL,
    CLA_LIB VARCHAR(100) NOT NULL,
    CLA_CREER_PAR VARCHAR(255) NOT NULL,
    CLA_MODIFIER_PAR VARCHAR(255),
    CLA_DATE_CREATION VARCHAR(50),
    CLA_VERSION INT NOT NULL,
    NIV_CODE INT NOT NULL,
    FIL_CODE INT NOT NULL,
    FOREIGN KEY (NIV_CODE) REFERENCES T_NIVEAU(NIV_CODE) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (FIL_CODE) REFERENCES T_FILIERE(FIL_CODE) ON DELETE CASCADE ON UPDATE CASCADE,
);

-- creating T_TYPE_PROFESSEUR table
CREATE TABLE T_TYPE_PROFESSEUR (
    TPR_CODE INT PRIMARY KEY NOT NULL,
    TPR_LIB VARCHAR(255) NOT NULL,
    TPR_TAUX_HORAIRE DECIMAL(10, 2) NOT NULL,
    TPR_CREER_PAR VARCHAR(255) NOT NULL,
    TPR_MODIFIER_PAR VARCHAR(255),
    TPR_DATE_CREATION VARCHAR(50),
    TPR_VERSION INT NOT NULL
);
