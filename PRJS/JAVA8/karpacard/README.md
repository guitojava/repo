#  MyTR AR Update Service #

### Quick Summary  

Technology stack choices:

* Spring-boot 1.5.9.RELEASE
* Swagger , Swagger2markup, SwaggerUI 
* Tomcat 
* Jersey for REST
* Jackson for JSON
* Postgresql DB 







### Documentation
	see     /docs/devnotes 
	see     /target/apiDoc
   
### Build 
    mvn clean install  

### DB Migrations 
	\src\main\resources\db\main-db-changelog.xml


### Generating Java from XSD   (JAXB/XSD/XJB)
	As part of the build we have some generated code from XSD in \target\generated-sources\ 
	In /main/resources we have the xsd and xjb files 


### Generating the Api Documentation as pdf and html 
	* mvn clean package -DskipTests=true 
	* find pdf and html in target/apiDoc
	* Skip doc generation by adding -DskipDocs=true   

### Local DEV  Environment  
    http://localhost:8000/swagger-ui/index.html
    http://localhost:8001/healthcheck.json
    http://localhost:8001/version.json


### DEV 
### UAT 
### PROD 





## Create DB 

	CREATE USER karpacard WITH PASSWORD 'karpacard';
	
	CREATE DATABASE karpacard
	 WITH ENCODING='UTF8'
	 OWNER=karpacard
	 CONNECTION LIMIT=25;
	
	GRANT ALL PRIVILEGES ON DATABASE karpacard to karpacard ;

Also check 

	show SERVER_ENCODING;   UTF8
	show TIMEZONE;   UTC 



curl -X GET "http://localhost:8000/api/v1/deals?accountNumber=1234567890&documentNumber=1234567890&documentType=I&documentDate=2001-01-01&senderEntityCode=test" -H  "accept: application/json"

http://localhost:8000/api/v1/deals?accountNumber=1234567890&documentNumber=1234567890&documentType=I&documentDate=2001-01-01&senderEntityCode=test

curl -X POST "http://localhost:8000/api/v1/deals?accountNumber=1234567890&documentNumber=1234567890&documentType=I&documentDate=2001-01-01&senderEntityCode=test" -H  "accept: application/json"

http://localhost:8000/api/v1/deals?accountNumber=1234567890&documentNumber=1234567890&documentType=I&documentDate=2001-01-01&senderEntityCode=test

	
Response body

{
  "id": 8,
  "accountNumber": "1234567890",
  "documentNumber": "1234567890",
  "documentType": "I",
  "documentDate": "2001-01-01",
  "senderEntityCode": "test",
  "openBalance": null,
  "financeCharges": null,
  "totalDue": null,
  "payments": null,
  "creationDate": null
}

	
### UAT  Environment 
 


### Useful links 
* Spring boot Configuration references
    * [Common application properties](https://docs.spring.io/spring-boot/docs/1.5.9.RELEASE/reference/html/common-application-properties.html)
* Logging 
    * [server logging patterns](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-logging.html)
    * [log4j pattern layout](http://logging.apache.org/log4j/2.x/manual/layouts.html#PatternLayout)
    * [Tomcat access log patter](https://tomcat.apache.org/tomcat-8.0-doc/config/valve.html)

    
   
### Versioning strategy
The following versioning scheme should be used in order to have a more consitent versions.

|  pattern                                | env  | Description   |
| ----------------------------------------|------|---------------|
| <major\>.<minor\>.<incr\>-SNAPSHOT      | dev  | Development version, not tagged in version control |
| <major\>.<minor\>.<incr\>.CR<cr_incr\>  | uat  | Candidate Release for QA or customer.              |
| <major\>.<minor\>.<incr\>               | prod | Released verison after test.                       |

Where the place holders are meant to be applied in the following way :

* **<major\>**: Major version nr (integer)  
Should only change if public interface changes and is no full backwards compatible with previous version.  
Is indication that code that calls the service may need to be changed when upgrading to new version.
* **<minor\>** : Minor version nr (integer)  
Should be incremented for each new (planned) release of the service.  
The changes could be function or bugfixing but also interface changes that don't break backwards compatibility with previous one.
* **<incr\>** : Incremental version nr (integer)  
Should be incremented in case of hotfixes/bugfixes.
* **<cr_incr\>** : Candidate Release Incremental version nr (integer)  
Incremented each time a new version is released/deployed for testing.


