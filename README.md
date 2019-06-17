# HospitalDatabase
A hospital database where you can manage your information as a doctor, staff, and patient.

This application will allow one to look through a hospital’s database for information if they are an administrator, patient or doctor. An administrator is able to edit patient info., edit appointment info, edit doctor info., edit hospital visits, check-in and check-out patients, and view appointments. A patient is able to view their appointment details and pay for their outstanding balance. A doctor is able to edit appointment information, view patient information, and edit treatment information.

## User interface specification: Menu based

MAIN MENU
- ADMINISTRATIVE FUNCTIONS
- PATIENT FUNCTIONS
- DOCTOR FUNCTIONS
- QUIT

ADMINISTRATIVE FUNCTIONS MENU
- Add/edit/remove patient
- Add/edit/remove appointment
- Add/edit/remove doctor
- Add/edit/remove hospital visit
- Check-in and check-out patient for their hospital visit
- View appointments
- Quit


PATIENT FUNCTIONS MENU
- View appointment
- Pay outstanding dues
- Quit

DOCTOR FUNCTIONS MENU
- Add/edit/remove appointment
- View patient information
- Add/edit/remove treatment
- Quit

## Schema Diagrams
HOSPVISIT (HOS_ID(int) not null,
- APP_ID(int) not null,
- admitDatetime(datetime),
- releaseDatetime(datetime),
- PRIMARY KEY(HOS_ID)
- FOREIGN KEY(APP_ID) REFERENCES APPOINTMENT(APP_ID)

APPOINTMENT(APP ID(int) not null,
- DOC ID(int) not null,
- PAT ID(int) not null,
- datetime(datetime),
- acost int (10,2),
- PRIMARY KEY (APP_ID),
- FOREIGN KEY(DOC_ID) REFERENCES DOCTOR(DOC_ID),
- FOREIGN KEY (PAT_ID) REFERENCES PATIENT(PAT_ID))

ILLNESS( ILL_NAME char(20) not null,
- Description char(200),
- PRIMARY KEY (ILL_NAME))

TREATMENT(TRE_NAME char(40) not null,
- ILL_NAME char(20),
- type char(20),
- tcost int (10,2),
- datetime(datetime),
- problemResolved char(1),
- PRIMARY KEY(TRE_NAME),
- FOREIGN KEY(ILL_NAME) REFERENCES ILLNESS(ILL_NAME))

DEPARTMENT (DEPT_NAME varchar (20) not null,
- locationWing varchar (20),
- chairman varchar (20),
- PRIMARY KEY(DEPT_NAME)) 

DOCTOR(DOC_ID varchar(20) not null,
- DEPT_NAME varchar (20),
- docName varchar (20),
- address varchar (20),
- phone varchar (20),
- PRIMARY KEY(DOC_ID),
- FOREIGN KEY (DEPT_NAME) REFERENCES DEPARTMENT (DEPT_NAME))

PATIENT (PAT_ID int (20) not null, 
- DOC_ID int (20) not null,
- ILL_NAME varchar(20),
- patName varchar(20),
- address varchar (20),
- phone varchar (20),
- currentDues int (10,2)
- PRIMARY KEY(PAT_ID),
- FOREIGN KEY (DOC_ID) REFERENCES DOCTOR (DOC_ID),
- FOREIGN KEY (ILL_NAME) REFERENCES ILLNESS (ILL_NAME))

ADMIN (ADM_ID int(20) not null,
- DEPT_NAME varchar(20),
- admName varchar(20),
- address varchar (20),
- phone varchar (20),
- PRIMARY KEY (ADM_ID),
- FOREIGN KEY (DEP_NAME) REFERENCES ADMIN (DEPT_NAME))
