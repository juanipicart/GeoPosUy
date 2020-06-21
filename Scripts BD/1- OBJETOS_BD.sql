spool OBJETOS_BD.log

prompt
prompt Creating table CARACTERISTICAS
prompt ==============================
prompt
create table CARACTERISTICAS
(
  ID_CARACTERISTICA NUMBER not null,
  DESCRIPCION       VARCHAR2(30 CHAR) not null,
  UNIDAD_MEDIDA     VARCHAR2(20 CHAR)
)
;
alter table CARACTERISTICAS
  add constraint PK_CARACTERISTICA primary key (ID_CARACTERISTICA);
alter table CARACTERISTICAS
  add constraint UK_CARACTERISTICA unique (DESCRIPCION);

prompt
prompt Creating table DEPARTAMENTOS
prompt ============================
prompt
create table DEPARTAMENTOS
(
  ID_DEPARTAMENTO NUMBER(2) not null,
  DEPARTAMENTO    VARCHAR2(35 CHAR) not null
)
;
alter table DEPARTAMENTOS
  add constraint PK_DEPARTAMENTO primary key (ID_DEPARTAMENTO);
alter table DEPARTAMENTOS
  add constraint UK_DEPARTAMENTO unique (DEPARTAMENTO);

prompt
prompt Creating table FENOMENOS
prompt ========================
prompt
create table FENOMENOS
(
  ID_FENOMENO         NUMBER not null,
  CODIGO              VARCHAR2(5 CHAR) not null,
  NOMBRE              VARCHAR2(20 CHAR) not null,
  DESCRIPCION         VARCHAR2(50 CHAR) not null,
  CONTACTO_EMERGENCIA VARCHAR2(20 CHAR)
)
;
alter table FENOMENOS
  add constraint PK_FENOMENO primary key (ID_FENOMENO);
alter table FENOMENOS
  add constraint UK_FENOMENO unique (CODIGO);

prompt
prompt Creating table LOCALIDADES
prompt ==========================
prompt
create table LOCALIDADES
(
  ID_LOCALIDAD NUMBER(5) not null,
  LOCALIDAD    VARCHAR2(40 CHAR) not null,
  ID_DEPTO     NUMBER(2)
)
;
alter table LOCALIDADES
  add constraint PK_LOCALIDAD primary key (ID_LOCALIDAD);
alter table LOCALIDADES
  add constraint FK_LOC_DEPTO foreign key (ID_DEPTO)
  references DEPARTAMENTOS (ID_DEPARTAMENTO);

prompt
prompt Creating table USUARIOS_ESTADOS
prompt ===============================
prompt
create table USUARIOS_ESTADOS
(
  ID_ESTADO   NUMBER not null,
  DESC_ESTADO VARCHAR2(30 CHAR)
)
;
alter table USUARIOS_ESTADOS
  add constraint PK_USU_DES_ESTADO primary key (ID_ESTADO);

prompt
prompt Creating table USUARIOS_TIPOS_DOC
prompt =================================
prompt
create table USUARIOS_TIPOS_DOC
(
  ID_TIPO_DOC NUMBER not null,
  TIPO        VARCHAR2(20 CHAR) not null
)
;
alter table USUARIOS_TIPOS_DOC
  add constraint PK_USU_TIPO_DOC primary key (ID_TIPO_DOC);

prompt
prompt Creating table ZONAS
prompt ====================
prompt
create table ZONAS
(
  ID_ZONA     NUMBER(2) not null,
  DESCRIPCION VARCHAR2(20 CHAR) not null
)
;
alter table ZONAS
  add constraint PK_ZONA primary key (ID_ZONA);

prompt
prompt Creating table ROLES_USUARIOS
prompt =============================
prompt
create table ROLES_USUARIOS
(
  ID_ROL          NUMBER not null,
  DESCRIPCION_ROL VARCHAR2(20 CHAR) not null
)
;
alter table ROLES_USUARIOS
  add constraint PK_ROL primary key (ID_ROL);

prompt
prompt Creating table USUARIOS
prompt =======================
prompt
create table USUARIOS
(
  ID_USER      NUMBER not null,
  USUARIO      VARCHAR2(25 CHAR) not null,
  NOMBRE       VARCHAR2(50 CHAR) not null,
  APELLIDO     VARCHAR2(50 CHAR) not null,
  DIRECCION    VARCHAR2(200 CHAR) not null,
  ROL          NUMBER not null,
  LOCALIDAD    NUMBER not null,
  ZONA         NUMBER not null,
  DEPARTAMENTO NUMBER not null,
  MAIL         VARCHAR2(100 CHAR) not null,
  PASSWORD     VARCHAR2(100 CHAR) not null,
  TIPO_DOC     NUMBER not null,
  DOCUMENTO    VARCHAR2(20 CHAR) not null,
  ESTADO       NUMBER not null
)
;
alter table USUARIOS
  add constraint PK_USUARIO primary key (ID_USER);
alter table USUARIOS
  add constraint FK_USU_DEPTO foreign key (DEPARTAMENTO)
  references DEPARTAMENTOS (ID_DEPARTAMENTO);
alter table USUARIOS
  add constraint FK_USU_ESTADO foreign key (ESTADO)
  references USUARIOS_ESTADOS (ID_ESTADO);
alter table USUARIOS
  add constraint FK_USU_LOCAL foreign key (LOCALIDAD)
  references LOCALIDADES (ID_LOCALIDAD);
alter table USUARIOS
  add constraint FK_USU_ROL foreign key (ROL)
  references ROLES_USUARIOS (ID_ROL);
alter table USUARIOS
  add constraint FK_USU_TIPO_DOC foreign key (TIPO_DOC)
  references USUARIOS_TIPOS_DOC (ID_TIPO_DOC);
alter table USUARIOS
  add constraint FK_USU_ZONA foreign key (ZONA)
  references ZONAS (ID_ZONA);

prompt
prompt Creating table OBSERVACIONES
prompt ============================
prompt
create table OBSERVACIONES
(
  ID_OBSERVACION           NUMBER not null,
  DESCRIPCION              VARCHAR2(100 CHAR) not null,
  GEOLOCALIZACION          VARCHAR2(50 CHAR) not null,
  FECHA_HORA               DATE not null,
  ID_USUARIO               NUMBER not null,
  NIVEL_CRITICIDAD         NUMBER(1) not null,
  ID_LOCALIDAD             NUMBER(5) not null,
  ID_DEPARTAMENTO          NUMBER(2) not null,
  ID_ZONA                  NUMBER(2) not null,
  REVISADO                 NUMBER(1) not null,
  OBSERVACIONES_VALIDACION VARCHAR2(100 CHAR),
  ACTIVO                   NUMBER(1) not null,
  ID_FENOMENO              NUMBER not null
)
;
comment on table OBSERVACIONES
  is 'Tabla con el registro de observaciones de los usuarios';
comment on column OBSERVACIONES.DESCRIPCION
  is 'Descripcion aportada por el visualizador';
comment on column OBSERVACIONES.GEOLOCALIZACION
  is 'Dato para geolocalizar la observacion';
comment on column OBSERVACIONES.FECHA_HORA
  is 'Fecha y Hora del suceso';
comment on column OBSERVACIONES.ID_USUARIO
  is 'Usuario que ingresa la observacion';
comment on column OBSERVACIONES.NIVEL_CRITICIDAD
  is '1-Alto, 2-Medio,3-Bajo, 4-Informe';
comment on column OBSERVACIONES.REVISADO
  is 'Representa si la observacion ha sido validada (0-NO, 1-SI)';
comment on column OBSERVACIONES.OBSERVACIONES_VALIDACION
  is 'Observacion ingresada por el validador';
comment on column OBSERVACIONES.ACTIVO
  is 'Valor del registro (1=activo, 0=inactivo)';
alter table OBSERVACIONES
  add constraint PK_OBSERVACION primary key (ID_OBSERVACION);
alter table OBSERVACIONES
  add constraint FK_OBS_FENOMENOS foreign key (ID_FENOMENO)
  references FENOMENOS (ID_FENOMENO);
alter table OBSERVACIONES
  add constraint FK_OBS_USUARIOS foreign key (ID_USUARIO)
  references USUARIOS (ID_USER);
alter table OBSERVACIONES
  add constraint FK_OBS_DEPTO foreign key (ID_DEPARTAMENTO)
  references DEPARTAMENTOS (ID_DEPARTAMENTO);
alter table OBSERVACIONES
  add constraint FK_OBS_LOCALIDAD foreign key (ID_LOCALIDAD)
  references LOCALIDADES (ID_LOCALIDAD);
alter table OBSERVACIONES
  add constraint FK_OBS_ZONA foreign key (ID_ZONA)
  references ZONAS (ID_ZONA);
alter table OBSERVACIONES
  add constraint CHK_OBSERVACIONES
  check (NIVEL_CRITICIDAD in (1,2,3,4));

prompt
prompt Creating table OBS_CARACTERISTICAS
prompt ==================================
prompt
create table OBS_CARACTERISTICAS
(
  ID_OBSERVACION    NUMBER not null,
  ID_CARACTERISTICA NUMBER not null,
  VALOR             VARCHAR2(15 CHAR)
)
;
alter table OBS_CARACTERISTICAS
  add constraint PK_OBS_CARACTERISTICAS primary key (ID_OBSERVACION, ID_CARACTERISTICA);
alter table OBS_CARACTERISTICAS
  add constraint FK_OBS_CARACT_CAR foreign key (ID_CARACTERISTICA)
  references CARACTERISTICAS (ID_CARACTERISTICA);
alter table OBS_CARACTERISTICAS
  add constraint FK_OBS_CARACT_OBS foreign key (ID_OBSERVACION)
  references OBSERVACIONES (ID_OBSERVACION);  

prompt
prompt Creating table PALABRAS_PROHIBIDAS
prompt ==================================
prompt
create table PALABRAS_PROHIBIDAS
(
  PALABRA VARCHAR2(20 CHAR) not null,
  DETALLE VARCHAR2(40 CHAR)
)
;
alter table PALABRAS_PROHIBIDAS
  add constraint PK_PALABRAS_PROHIBIDAS primary key (PALABRA);

prompt
prompt Creating sequence ID_CARACTERISTICA_SEQ
prompt =======================================
prompt
create sequence ID_CARACTERISTICA_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
nocache;

prompt
prompt Creating sequence ID_DEPARTAMENTO_SEQ
prompt =====================================
prompt
create sequence ID_DEPARTAMENTO_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 20
increment by 1
nocache;

prompt
prompt Creating sequence ID_FENOMENO_SEQ
prompt =================================
prompt
create sequence ID_FENOMENO_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
nocache;

prompt
prompt Creating sequence ID_LOCALIDAD_SEQ
prompt ==================================
prompt
create sequence ID_LOCALIDAD_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 2028
increment by 1
nocache;

prompt
prompt Creating sequence ID_OBSERVACION_SEQ
prompt ====================================
prompt
create sequence ID_OBSERVACION_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
nocache;

prompt
prompt Creating sequence ID_ROL_USUARIO_SEQ
prompt ====================================
prompt
create sequence ID_ROL_USUARIO_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 11
increment by 1
nocache;

prompt
prompt Creating sequence ID_TIPO_DOC_SEQ
prompt =================================
prompt
create sequence ID_TIPO_DOC_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 5
increment by 1
nocache;

prompt
prompt Creating sequence ID_USER_SEQ
prompt =============================
prompt
create sequence ID_USER_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 2
increment by 1
nocache;

prompt
prompt Creating sequence ID_USUARIOS_ESTADOS_SEQ
prompt =========================================
prompt
create sequence ID_USUARIOS_ESTADOS_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 4
increment by 1
nocache;

prompt
prompt Creating sequence ID_ZONA_SEQ
prompt =============================
prompt
create sequence ID_ZONA_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 8
increment by 1
nocache;

prompt
prompt Creating trigger CARACTERISTICAS_TRG
prompt ====================================
prompt
CREATE OR REPLACE TRIGGER CARACTERISTICAS_TRG
BEFORE INSERT ON CARACTERISTICAS
FOR EACH ROW
BEGIN
    SELECT ID_CARACTERISTICA_SEQ.NEXTVAL
    INTO :NEW.ID_CARACTERISTICA
    FROM DUAL;
END;
/

prompt
prompt Creating trigger DEPARTAMENTOS_TRG
prompt ==================================
prompt
CREATE OR REPLACE TRIGGER DEPARTAMENTOS_TRG
BEFORE INSERT ON departamentos
FOR EACH ROW
BEGIN
    SELECT ID_DEPARTAMENTO_SEQ.nextval
    INTO :new.ID_DEPARTAMENTO
    FROM dual;
END;
/

prompt
prompt Creating trigger FENOMENOS_TRG
prompt ==============================
prompt
CREATE OR REPLACE TRIGGER FENOMENOS_TRG
BEFORE INSERT ON FENOMENOS
FOR EACH ROW
BEGIN
    SELECT  ID_FENOMENO_SEQ.NEXTVAL
    INTO :NEW.ID_FENOMENO
    FROM DUAL;
END;
/

prompt
prompt Creating trigger LOCALIDADES_TRG
prompt ================================
prompt
CREATE OR REPLACE TRIGGER LOCALIDADES_TRG
BEFORE INSERT ON LOCALIDADES
FOR EACH ROW
BEGIN
	  SELECT ID_LOCALIDAD_SEQ.nextval
    INTO :new.ID_LOCALIDAD
    FROM dual;
END;
/

prompt
prompt Creating trigger OBSERVACIONES_TRG
prompt ==================================
prompt
CREATE OR REPLACE TRIGGER OBSERVACIONES_TRG
BEFORE INSERT ON observaciones
FOR EACH ROW
BEGIN
    SELECT ID_OBSERVACION_seq.nextval
    INTO :new.ID_OBSERVACION
    FROM dual;
END;
/

prompt
prompt Creating trigger ROLES_USUARIOS_TRG
prompt ===================================
prompt
CREATE OR REPLACE TRIGGER ROLES_USUARIOS_TRG
BEFORE INSERT ON roles_usuarios
FOR EACH ROW
BEGIN
    SELECT ID_ROL_USUARIO_seq.nextval
    INTO :new.ID_ROL
    FROM dual;
END;
/

prompt
prompt Creating trigger USUARIOS_ESTADOS_TRG
prompt =====================================
prompt
CREATE OR REPLACE TRIGGER USUARIOS_ESTADOS_TRG
BEFORE INSERT ON USUARIOS_ESTADOS
FOR EACH ROW
BEGIN
    SELECT ID_USUARIOS_ESTADOS_SEQ.nextval
    INTO :new.ID_ESTADO
    FROM dual;
END;
/

prompt
prompt Creating trigger USUARIOS_TIPOS_DOC_TRG
prompt =======================================
prompt
CREATE OR REPLACE TRIGGER USUARIOS_TIPOS_DOC_TRG
BEFORE INSERT ON USUARIOS_TIPOS_DOC
FOR EACH ROW
BEGIN
    SELECT ID_TIPO_DOC_SEQ.nextval
    INTO :new.ID_TIPO_DOC
    FROM dual;
END;
/

prompt
prompt Creating trigger USUARIOS_TRG
prompt =============================
prompt
CREATE OR REPLACE TRIGGER USUARIOS_TRG
BEFORE INSERT ON USUARIOS
FOR EACH ROW
BEGIN
    SELECT ID_USER_SEQ.nextval
    INTO :new.ID_USER
    FROM dual;
END;
/

prompt
prompt Creating trigger ZONAS_TRG
prompt ==========================
prompt
CREATE OR REPLACE TRIGGER ZONAS_TRG
BEFORE INSERT ON ZONAS
FOR EACH ROW
BEGIN
    SELECT id_zona_seq.nextval
    INTO :new.id_zona
    FROM dual;
END;
/


spool off
