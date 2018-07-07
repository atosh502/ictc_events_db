
DROP DATABASE IF EXISTS dbms_project;

CREATE DATABASE dbms_project;

USE dbms_project;

CREATE TABLE IF NOT EXISTS event(
    eventId BIGINT AUTO_INCREMENT,
    eventName VARCHAR(255) NOT NULL,
    eventDescription VARCHAR(255),
    expectedNumberOfParticipants INT,
    eventDurationInDays INT NOT NULL,
    eventStartDate VARCHAR(255),
    eventEndDate VARCHAR(255),
    accepted INT DEFAULT 0,
    PRIMARY KEY(eventId)
    -- TODO: add foreign key to admin and organizer
);

CREATE TABLE IF NOT EXISTS admin(
    userId BIGINT AUTO_INCREMENT,
    userName VARCHAR(255) NOT NULL UNIQUE,
    userPassword VARCHAR(255) NOT NULL ,
    PRIMARY KEY(userId)
    -- TODO: add foreign key to accepted events
);

CREATE TABLE IF NOT EXISTS organizer(
    userId BIGINT AUTO_INCREMENT,
    userName VARCHAR(255) NOT NULL UNIQUE,
    userPassword VARCHAR(255) NOT NULL,
    organizerName VARCHAR(255),
    organizerEmail VARCHAR(255),
    organizerAddress VARCHAR(255),
    organizerPhone VARCHAR(255),
    PRIMARY KEY(userId)
);


CREATE TABLE IF NOT EXISTS eventSection(
    eventSectionId BIGINT AUTO_INCREMENT,
    eventSectionDate VARCHAR(255),
    eventSectionTime VARCHAR(255),
    PRIMARY KEY(eventSectionId)
    -- TODO: add foreign key to event
    -- TODO: add foreign key to rooms
);


CREATE TABLE IF NOT EXISTS room(
    roomId BIGINT AUTO_INCREMENT,
    roomName VARCHAR(255),
    roomCapacity INT,
    roomFloor INT,
	roomCategory VARCHAR(255),
	costPerDay INT,
	costPerHour INT,
	costPerUnit INT,
	PRIMARY KEY(roomId)
	-- TODO: add foreign key to eventSections
);

CREATE TABLE IF NOT EXISTS admin_approves_event(
    userId BIGINT,
    eventId BIGINT,
    FOREIGN KEY (userId)
    REFERENCES admin(userId)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (eventId)
    REFERENCES event(eventId)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS organizer_creates_event(
    userId BIGINT,
    eventId BIGINT,
    FOREIGN KEY (userId)
    REFERENCES organizer(userId)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (eventId)
    REFERENCES event(eventId)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS event_contains_eventSection(
    eventId BIGINT,
    eventSectionId BIGINT,
    FOREIGN KEY (eventId)
    REFERENCES event(eventId)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (eventSectionId)
    REFERENCES eventSection(eventSectionId)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS eventSection_happensIn_room(
    eventSectionId BIGINT,
    roomId BIGINT,
    FOREIGN KEY (eventSectionId)
    REFERENCES eventSection(eventSectionId)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (roomId)
    REFERENCES room(roomId)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


