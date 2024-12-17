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
    RUB_VERSION INT NOT NULL,
    CLA_CODE INT NOT NULL,
    FOREIGN KEY (CLA_CODE) REFERENCES T_CLASSE(CLA_CODE)
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

CREATE TABLE T_PROMOTIONS (
    PRO_CODE INT PRIMARY KEY NOT NULL,
    PRO_LIB VARCHAR(255) NOT NULL,
    PRO_CREER_PAR VARCHAR(255) NOT NULL,
    PRO_MODIFIER_PAR VARCHAR(255),
    PRO_DATE_CREATION VARCHAR(50),
    PRO_VERSION INT NOT NULL
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
    FOREIGN KEY (NIV_CODE) REFERENCES T_NIVEAU(NIV_CODE),
    FOREIGN KEY (FIL_CODE) REFERENCES T_FILIERE(FIL_CODE),
);

CREATE TABLE T_ETUDIANT (
    ETD_CODE INT PRIMARY KEY NOT NULL,
    ETD_PRENOM VARCHAR(255) NOT NULL,
    ETD_NOM VARCHAR(255) NOT NULL,
    ETD_SEXE VARCHAR(1) NOT NULL,
    ETD_DATE_NAISSANCE VARCHAR(100) NOT NULL,
    ETD_CREER_PAR VARCHAR(100) NOT NULL,
    ETD_MODIFIER_PAR VARCHAR(100),
    ETD_PHONE VARCHAR(50),
    ETD_ADDRESSE VARCHAR(100),
    ETD_EMAIL VARCHAR(100),
    NAT_CODE INT NOT NULL,
    ETD_VERSION INT NOT NULL,
    FOREIGN KEY (NAT_CODE) REFERENCES T_NATIONALITE(NAT_CODE),
);

CREATE TABLE T_PERSONNE_CONTACT (
    CON_CODE INT PRIMARY KEY NOT NULL,
    CON_PRENOM VARCHAR(255) NOT NULL,
    CON_NOM VARCHAR(255) NOT NULL,
    CON_PHONE VARCHAR(50) NOT NULL,
    CON_ADDRESSE VARCHAR(100) NOT NULL,
    CON_EMAIL VARCHAR(100),
    LIE_CODE INT NOT NULL,
    ETD_CODE INT NOT NULL,
    CON_VERSION INT NOT NULL,
    FOREIGN KEY (LIE_CODE) REFERENCES T_LIEN(LIE_CODE),
    FOREIGN KEY (ETD_CODE) REFERENCES T_ETUDIANT(ETD_CODE)
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

-- creating T_CLASSE_MATIERE table
CREATE TABLE T_CLASSE_MATIERE (
    CLM_CODE INT PRIMARY KEY NOT NULL,
    CLM_VOLUME_HORAIRE INT NOT NULL,
    CLM_CREER_PAR VARCHAR(255) NOT NULL,
	CLM_MODIFIER_PAR VARCHAR(255),
	CLM_DATE_CREATION VARCHAR(50),
	CLM_VERSION INT NOT NULL,
    CLA_CODE INT NOT NULL,
    MAT_CODE INT NOT NULL,
    FOREIGN KEY (CLA_CODE) REFERENCES T_CLASSE(CLA_CODE),
    FOREIGN KEY (MAT_CODE) REFERENCES T_MATIERE(MAT_CODE)
); 

-- creating emploi table
CREATE TABLE T_EMPLOI (
    EMP_CODE INT PRIMARY KEY NOT NULL, 
    EMP_LIB VARCHAR(255) NOT NULL,
    EMP_CREER_PAR VARCHAR(255) NOT NULL, 
    EMP_MODIFIER_PAR VARCHAR(255),
    EMP_DATE_CREATION VARCHAR(50), 
    EMP_VERSION INT
);

-- creating Direction table
CREATE TABLE T_DIRECTION (
    DIR_CODE INT PRIMARY KEY NOT NULL,
    DIR_LIB VARCHAR(255) NOT NULL,
    DIR_CREER_PAR VARCHAR(255) NOT NULL,
    DIR_MODIFIER_PAR VARCHAR(255),
    DIR_DATE_CREATION VARCHAR(100),
    DIR_VERSION INT NOT NULL
);

-- creating service table
CREATE TABLE T_SERVICE (
    SCE_CODE INT PRIMARY KEY NOT NULL,
    SCE_LIB VARCHAR(255) NOT NULL,
    SCE_CREER_PAR VARCHAR(255) NOT NULL,
    SCE_MODIFIER_PAR VARCHAR(255),
    SCE_DATE_CREATION VARCHAR(100),
    SCE_VERSION INT NOT NULL,
    DIR_CODE INT NOT NULL,
    FOREIGN KEY (DIR_CODE) REFERENCES T_DIRECTION(DIR_CODE) ON DELETE CASCASE ON UPDATE CASCADE
);

-- personnel table
CREATE TABLE T_PERSONNEL (
    PER_CODE INT PRIMARY KEY NOT NULL,
    PER_NOM VARCHAR(255) NOT NULL,
    PER_PRENOM VARCHAR(255) NOT NULL,
    PER_SEXE VARCHAR(10) NOT NULL,
    PER_DATE_DE_NAISSANCE VARCHAR(100) NOT NULL,
    PER_ADDRESSE VARCHAR(255) NOT NULL,
    PER_EMAIL VARCHAR(100),
    PER_TELEPHONE VARCHAR(50) NOT NULL,
    PER_CREER_PAR VARCHAR(255) NOT NULL,
    PER_MODIFIER_PAR VARCHAR(255),
    PER_DATE_CREATION VARCHAR(50),
    PER_VERSION INT NOT NULL,
    EMP_CODE INT NOT NULL, 
    SCE_CODE INT NOT NULL,
    DIR_CODE INT NOT NULL,
    FOREIGN KEY (EMP_CODE) REFERENCES T_EMPLOI(EMP_CODE),
    FOREIGN KEY (SCE_CODE) REFERENCES T_SERVICE(SCE_CODE),
    FOREIGN KEY (DIR_CODE) REFERENCES T_DIRECTION(DIR_CODE)
);


-- creating lien table
CREATE TABLE T_LIEN (
    LIE_CODE INT PRIMARY KEY NOT NULL,
    LIE_LIB VARCHAR(255) NOT NULL,
    LIE_CREER_PAR VARCHAR(255) NOT NULL,
    LIE_MODIFIER_PAR VARCHAR(255),
    LIE_DATE_CREATION VARCHAR(50),
    LIE_VERSION INT NOT NULL 
);

CREATE TABLE T_NATIONALITE (
    NAT_CODE INT PRIMARY KEY NOT NULL,
    NAT_LIB VARCHAR(255) NOT NULL,
    NAT_CREER_PAR VARCHAR(255) NOT NULL, 
    NAT_MODIFIER_PAR VARCHAR(255),
    NAT_DATE_CREATION VARCHAR(50),
    NAT_VERSION INT NOT NULL
);

CREATE TABLE T_TYPE_DOCUMENT (
    TDC_CODE INT PRIMARY KEY NOT NULL,
    TDC_LIB VARCHAR(255) NOT NULL,
    TDC_CREER_PAR VARCHAR(255) NOT NULL, 
    TDC_MODIFIER_PAR VARCHAR(255),
    TDC_DATE_CREATION VARCHAR(50),
    TDC_VERSION INT NOT NULL
);


SELECT CLA_CODE, CLA_LIB FROM T_CLASSE CLA, T_FILIERE FIL, T_NIVEAU NIV WHERE CLA.FIL_CODE = FIL.FIL_CODE AND CLA.NIV_CODE = NIV.NIV_CODE;



-- creating Devise table
CREATE TABLE T_DEVISE (
    DEV_CODE INT PRIMARY KEY NOT NULL,
    DEV_LIB VARCHAR(100) NOT NULL,
    DEV_STATUS BIT NOT NULL,
    DEV_CREER_PAR VARCHAR(255) NOT NULL,
    DEV_MODIFIER_PAR VARCHAR(255),
    DEV_DATE_CREATION VARCHAR(50),
    DEV_VERSION INT NOT NULL
);

-- personnel table

/* Implementation JPA de ce script */
CREATE TABLE T_PERSONNEL (
    PER_CODE INT PRIMARY KEY NOT NULL,
    PER_NOM VARCHAR(255) NOT NULL,
    PER_PRENOM VARCHAR(255) NOT NULL,
    PER_SEXE VARCHAR(10) NOT NULL,
    PER_DATE_DE_NAISSANCE VARCHAR(100) NOT NULL,
    PER_ADDRESSE VARCHAR(255) NOT NULL,
    PER_EMAIL VARCHAR(100),
    PER_TELEPHONE VARCHAR(50) NOT NULL,
    PER_CREER_PAR VARCHAR(255) NOT NULL,
    PER_MODIFIER_PAR VARCHAR(255),
    PER_DATE_CREATION VARCHAR(50),
    PER_VERSION INT NOT NULL,
    EMP_CODE INT NOT NULL, 
    SCE_CODE INT NOT NULL,
    DIR_CODE INT NOT NULL,
    FOREIGN KEY (EMP_CODE) REFERENCES T_EMPLOI(EMP_CODE) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (SCE_CODE) REFERENCES T_SERVICE(SCE_CODE) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (DIR_CODE) REFERENCES T_DIRECTION(DIR_CODE)
);

CREATE TABLE T_DOCUMENT (
    DOC_CODE INT IDENTITY PRIMARY KEY,
    DOC_TYPE VARCHAR(50) NOT NULL,
    DOC_NAME VARCHAR(255) NOT NULL,
    DOC_FILE VARCHAR(255) NOT NULL
);

CREATE TABLE T_INSCRIPTION (
    INS_CODE INT IDENTITY PRIMARY KEY,
    INS_DATE VARCHAR(50) NOT NULL,
    INS_NIVEAU_VALIDATION VARCHAR(255) NOT NULL,
    INS_CREER_PAR VARCHAR(255) NOT NULL,
    INS_MODIFIER_PAR VARCHAR(255) NOT NULL,
    INS_VERSION VARCHAR(255) NOT NULL,
    ETD_CODE INT NOT NULL,
    PRO_CODE INT NOT NULL,
    AAC_CODE INT NOT NULL,
    CLA_CODE INT NOT NULL
);
