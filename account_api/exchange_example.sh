echo "Testing Account API..." && echo '' && \
echo "Getting current exchange rate for 100 DKK..." && \
curl -X GET http://localhost:8080/exchange/current/100.00 \
-H "Content-Type: application/json" \
-d '' \
&& echo '' && echo '' && \
echo "Script completed."