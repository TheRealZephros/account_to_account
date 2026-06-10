# account_to_account

## Prerequisites
java 17+

## To use the exchange API
in the application.properties file (account_api/src/resources/application.properties) add a valid API key for https://app.exchangerate-api.com/

## Run the application

### bash:
./mvnw spring-boot:run

### cmd:
mvnw.cmd spring-boot:run

## Examples of how to hit the endpoints
### Create an account
curl -X POST http://localhost:8080/accounts/create \\ \
-H "Content-Type: application/json" \\ \
-d '{"firstName": "John", "lastName": "Doe"}'

### Get the balance on an account with accountNumber = 1
curl -X GET http://localhost:8080/accounts/1/balance \\ \
-H "Content-Type: application/json" \\ \
-d ''

### Deposit 100.00 to an account with accountNumber = 1
curl -X POST http://localhost:8080/accounts/1/deposit \\ \
-H "Content-Type: application/json" \\ \
-d '{"amount": "100.00"}'

### Transferring 50.00 from account 1 to account 2
curl -X POST http://localhost:8080/transfer/ \\ \
-H "Content-Type: application/json" \\ \
-d '{"fromAccountNumber": 1, "toAccountNumber": 2, "amount": "50.00"}'


## Example.sh
The provided shell script example.sh will run through each of the endpoints shown above and print the results of each step.