echo "Testing Account API..." && echo '' && \
echo "Creating account..." && \
curl -X POST http://localhost:8080/accounts/create \
-H "Content-Type: application/json" \
-d '{"firstName": "John", "lastName": "Doe"}' \
&& echo '' && echo '' && \
echo "Getting account balance..." && \
curl -X GET http://localhost:8080/accounts/1/balance \
-H "Content-Type: application/json" \
-d '' \
&& echo '' &&\
echo "Depositing to account..." && \
curl -X POST http://localhost:8080/accounts/1/deposit \
-H "Content-Type: application/json" \
-d '{"amount": "100.00"}' \
&& echo '' && \
echo "Getting account balance after deposit..." && \
curl -X GET http://localhost:8080/accounts/1/balance \
-H "Content-Type: application/json" \
-d '' \
&& echo '' \
