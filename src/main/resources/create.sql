
CREATE TABLE city(
   ID INTEGER PRIMARY KEY  AutoIncrement   NOT NULL,
   NAME           VARCHAR(64)   NOT NULL,
   lat            FLOAT(5, 12) NULL,
   lon        FLOAT(5, 12) ,
   country         VARCHAR(64),
   admin1         VARCHAR(64),
   url         VARCHAR(64),
   featureCode         VARCHAR(64),
   timezone         VARCHAR(64),
   asl         INTEGER,
   population         long,
   distance         FLOAT(12, 4)
);


CREATE TABLE location(
   ID INTEGER PRIMARY KEY  AutoIncrement   NOT NULL,
   NAME           VARCHAR(64)   NOT NULL,
   lat            VARCHAR(32) NULL,
   lon        VARCHAR(32) ,
   country         VARCHAR(64),
   admin1         VARCHAR(64),
   url         VARCHAR(64),
   feature_code         VARCHAR(64),
   time_zone         VARCHAR(64),
   asl         VARCHAR(32),
   population         VARCHAR(32),
   distance         VARCHAR(32)
);

































