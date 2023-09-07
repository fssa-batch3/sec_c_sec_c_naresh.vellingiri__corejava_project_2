
# Share To Rise Backend Core Java Project


## Problem Statement

### The Challenge:

My friend, Ranjith, is a highly skilled and dedicated athlete with immense potential. He has dreamt of a successful career in football since his childhood. However, Ranjith faces a major hurdle: the lack of funds to support his training, coaching, and participation in crucial competitions.

### The Solution:

I aim to create a sports fundraising website that will empower Ranjith and other aspiring athletes to overcome the financial obstacles on their path to success. This website will serve as a platform for many players to raise funds, build a community of supporters, and achieve their sports career aspirations.


## Fundraiser table
This repository contains the SQL schema definition for a "fundraiser" database table. The "fundraiser" table is designed to store information about fundraising campaigns.

## Table Structure

The "fundraiser" table has the following structure:

| Column Name      | Data Type    | Constraints                  |
|------------------|--------------|------------------------------|
| id               | INT          | AUTO_INCREMENT, PRIMARY KEY  |
| title            | VARCHAR(30)  | NOT NULL                     |
| description      | VARCHAR(300) | NOT NULL                     |
| funding_goal     | DOUBLE       | NOT NULL                     |
| image_url        | VARCHAR(200) | NOT NULL                     |
| fund_ending_date | DATE         | NOT NULL                     |

## Constraints

- The `id` column is an auto-incrementing integer and serves as the primary key for the table.
- The `title` and `description` columns are both of type VARCHAR and must not be empty (NOT NULL).
- The `funding_goal` column is of type DOUBLE and must not be empty (NOT NULL). Additionally, a CHECK constraint ensures that the funding goal is at least 2000.
- The `image_url` column is a VARCHAR that stores the URL of an image for the campaign and must not be empty (NOT NULL).
- The `fund_ending_date` column is of type DATE and represents the date when the fundraising campaign will end. It must not be empty (NOT NULL).

## Usage

You can use this SQL schema to create the "fundraiser" table in your database system. Simply copy and paste the provided SQL code into your SQL client or management tool.

```sql
CREATE TABLE fundraiser (
  id INT AUTO_INCREMENT,
  title VARCHAR(30) NOT NULL,
  description VARCHAR(300) NOT NULL,
  funding_goal DOUBLE NOT NULL,
  image_url VARCHAR(200) NOT NULL,
  fund_ending_date DATE NOT NULL,
  PRIMARY KEY(id),
  CHECK(funding_goal >= 2000)
);
```

# Certificates Database Table

This repository contains the SQL schema definition for a "certificates" database table. The "certificates" table is designed to store information about certificates related to fundraising campaigns.

## Table Structure

The "certificates" table has the following structure:

| Column Name   | Data Type    | Constraints                       |
|---------------|--------------|-----------------------------------|
| id            | INT          | AUTO_INCREMENT, PRIMARY KEY       |
| fundraiser_id | INT          | FOREIGN KEY (references fundraiser(id)) |
| cert_number   | VARCHAR(50)  |                                  |
| cert_image_url| VARCHAR(300) |                                  |

## Relationships

- The `fundraiser_id` column is a foreign key that references the `id` column in the "fundraiser" table, establishing a relationship between certificates and fundraising campaigns.

## Usage

You can use this SQL schema to create the "certificates" table in your database system. Simply copy and paste the provided SQL code into your SQL client or management tool.

```sql
CREATE TABLE certificates (
  id INT AUTO_INCREMENT,
  fundraiser_id INT,
  cert_number VARCHAR(50),
  cert_image_url VARCHAR(300),
  PRIMARY KEY (id),
  FOREIGN KEY (fundraiser_id) REFERENCES fundraiser(id)
);
```

# Users Database Table

This repository contains the SQL schema definition for a "users" database table. The "users" table is designed to store information about users.

## Table Structure

The "users" table has the following structure:

| Column Name | Data Type  | Constraints                            |
|-------------|------------|----------------------------------------|
| user_id     | BIGINT     | AUTO_INCREMENT, PRIMARY KEY           |
| first_name  | VARCHAR(30)| NOT NULL                               |
| last_name   | VARCHAR(30)| NOT NULL                               |
| email       | VARCHAR(50)| NOT NULL                               |
| phone       | BIGINT     | NOT NULL, UNIQUE                       |
| password    | VARCHAR(30)| NOT NULL                               |
| is_active   | BOOLEAN    | NOT NULL, DEFAULT TRUE                 |

## Constraints

- The `user_id` column is a BIGINT and serves as the primary key for the table. It is auto-incremented.
- The `first_name` and `last_name` columns are of type VARCHAR and must not be empty (NOT NULL).
- The `email` column is of type VARCHAR and must not be empty (NOT NULL).
- The `phone` column is of type BIGINT, must not be empty (NOT NULL), and enforces uniqueness among users.
- The `password` column is of type VARCHAR and must not be empty (NOT NULL).
- The `is_active` column is a BOOLEAN that represents whether the user is active and defaults to TRUE.

## Usage

You can use this SQL schema to create the "users" table in your database system. Simply copy and paste the provided SQL code into your SQL client or management tool.

```sql
CREATE TABLE IF NOT EXISTS users (
  user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  email VARCHAR(50) NOT NULL,
  phone BIGINT NOT NULL UNIQUE,
  password VARCHAR(30) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE
);



