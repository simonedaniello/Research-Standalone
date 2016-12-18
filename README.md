# researchISPW

ResearchSTDALN was developed for the exam "Software Engineering and Web Application".
This standalone represents a part of an e-commerce web application, showing how research works.

## Prerequisites

this standalone needs a database system to run.
PostgreSQL was used in development but everyone can change database system modifying Provider class (src/databaseINIT/Provider).
You have to create you're own database and connect it on you're IDE.
After you've done it, you can run DBinit.sql (src/databaseINIT/DBinit.sql) and it will create schemas on this database automatically.

##Development environment

* IDEA IntelliJ - IDE
* Postgresql 9.4.1210 - Database system