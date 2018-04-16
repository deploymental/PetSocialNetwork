CREATE TABLE public.accounts (
  id                INTEGER PRIMARY KEY auto_increment,
  "name"            varchar(30) NOT NULL,
  surname           varchar(30) NOT NULL,
  middlename        varchar(30),
  sex               varchar(30) NOT NULL,
  birthdate         date NOT NULL,
  homeaddress       varchar(150),
  workaddress       varchar(150),
  icq               varchar(30),
  skype             varchar(30),
  additionalinfo    varchar(1000),
  image             bytea,
  registrationdate  date NOT NULL DEFAULT CURRENT_DATE,
  email             varchar(30) NOT NULL,
  "password"        varchar(30) NOT NULL,
  isadmin           boolean NOT NULL DEFAULT false,
  /* Keys */
  CONSTRAINT accounts_pkey
    PRIMARY KEY (id),
  CONSTRAINT accounts_email_key
    UNIQUE (email));

CREATE TABLE public.groupsaccounts (
  id          INTEGER PRIMARY KEY auto_increment,
  group_id    integer NOT NULL,
  account_id  integer NOT NULL,
  /* Keys */
  CONSTRAINT groupsaccounts_pkey
    PRIMARY KEY (id),
  CONSTRAINT groupsaccounts_group_id_account_id_key
    UNIQUE (group_id, account_id),
  /* Foreign keys */
  CONSTRAINT account_id
    FOREIGN KEY (account_id)
    REFERENCES public.accounts(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE public.messages (
  id         INTEGER PRIMARY KEY auto_increment,
  sender_id  integer NOT NULL,
  recipient  integer NOT NULL,
  "date"     date NOT NULL DEFAULT CURRENT_DATE,
  "text"     varchar(1000) NOT NULL,
  isgroup    boolean NOT NULL,
  /* Keys */
  CONSTRAINT messages_pkey
    PRIMARY KEY (id),
  /* Foreign keys */
  CONSTRAINT senders_id
    FOREIGN KEY (sender_id)
    REFERENCES public.accounts(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE public.accountsrelations (
  id            INTEGER PRIMARY KEY auto_increment,
  sender_id     integer NOT NULL,
  recipient_id  integer NOT NULL,
  relation      varchar(30) NOT NULL,
  "date"        date NOT NULL DEFAULT CURRENT_DATE,
  /* Keys */
  CONSTRAINT accountsrelations_pkey
    PRIMARY KEY (id),
  CONSTRAINT accountsrelations_sender_id_recipient_id_key
    UNIQUE (sender_id, recipient_id),
  /* Foreign keys */
  CONSTRAINT recipient_id
    FOREIGN KEY (recipient_id)
    REFERENCES public.accounts(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT sender_id
    FOREIGN KEY (sender_id)
    REFERENCES public.accounts(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE public.phones (
  id       INTEGER PRIMARY KEY auto_increment,
  account  integer NOT NULL,
  number   varchar(30) NOT NULL,
  /* Keys */
  CONSTRAINT phones_pkey
    PRIMARY KEY (id),
  /* Foreign keys */
  CONSTRAINT accounts_id
    FOREIGN KEY (account)
    REFERENCES public.accounts(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

  CREATE TABLE public.groups (
  id           INTEGER PRIMARY KEY auto_increment,
  creator_id   integer NOT NULL,
  "name"       varchar(30) NOT NULL,
  description  varchar(500) NOT NULL,
  image        bytea,
  /* Keys */
  CONSTRAINT groups_pkey
    PRIMARY KEY (id),
  CONSTRAINT groups_name_key
    UNIQUE ("name"),
  /* Foreign keys */
  CONSTRAINT creator_id
    FOREIGN KEY (creator_id)
    REFERENCES public.accounts(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
