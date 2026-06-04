echo "Testing Account API..." && echo '' && \
echo "Creating account..." && \
curl -X POST http://localhost:8080/accounts/create \
-H "Content-Type: application/json" \
-d '{"firstName": "John", "lastName": "Doe"}' \
&& echo '' && echo '' && \
echo "Getting account 1 balance..." && \
curl -X GET http://localhost:8080/accounts/1/balance \
-H "Content-Type: application/json" \
-d '' \
&& echo '' && echo '' && \
echo "Depositing 100 into account 1..." && \
curl -X POST http://localhost:8080/accounts/1/deposit \
-H "Content-Type: application/json" \
-d '{"amount": "100.00"}' \
&& echo '' && \
echo "Getting account 1 balance after deposit..." && \
curl -X GET http://localhost:8080/accounts/1/balance \
-H "Content-Type: application/json" \
-d '' \
&& echo '' && echo '' && \
echo "Creating another account..." && \
curl -X POST http://localhost:8080/accounts/create \
-H "Content-Type: application/json" \
-d '{"firstName": "John", "lastName": "Doe"}' \
&& echo '' && \
echo "Getting account 2 balance..." && \
curl -X GET http://localhost:8080/accounts/2/balance \
-H "Content-Type: application/json" \
-d '' \
&& echo '' && echo '' && \
echo "Transferring 50.00 from account 1 to account 2..." && \
curl -X POST http://localhost:8080/transfer/ \
-H "Content-Type: application/json" \
-d '{"fromAccountNumber": 1, "toAccountNumber": 2, "amount": "50.00"}' \
&& echo '' && \
echo "Getting account 1 balance after transfer..." && \
curl -X GET http://localhost:8080/accounts/1/balance \
-H "Content-Type: application/json" \
-d '' \
&& echo '' && \
echo "Getting account 2 balance after transfer..." && \
curl -X GET http://localhost:8080/accounts/2/balance \
-H "Content-Type: application/json" \
-d '' \
&& echo '' && echo '' && \
echo "Script completed."