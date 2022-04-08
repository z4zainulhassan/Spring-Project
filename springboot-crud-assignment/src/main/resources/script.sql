CREATE TABLE departments
   ( department_id NUMBER(4)
   , department_name VARCHAR2(30)
   , manager_id NUMBER(6)
   ) ;
   
ALTER TABLE departments
    ADD ( 
   PRIMARY KEY(department_id)
   ) ;   
   
   CREATE TABLE employees
   ( employee_id NUMBER(6)
   , first_name VARCHAR2(20)
   , last_name VARCHAR2(25)   
   , email VARCHAR2(25)
   , phone_number VARCHAR2(20)
   , hire_date DATE
   , salary NUMBER(8,2)
   , manager_id NUMBER(6)
   , department_id NUMBER(4)   
   ) ;
   
   
ALTER TABLE employees
         ADD ( 
   PRIMARY KEY(employee_id)
   ) ; 