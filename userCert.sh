#!/bin/bash

if [[ $# -eq 0 ]]; then
  echo "Usage: $0 <name>"
  exit 1
fi

name="id"  

openssl req -new -newkey rsa:4096 -nodes -keyout "${name}.key" -out "${name}.csr"

openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in "${name}.csr" -out "${name}.crt" -days 365 -CAcreateserial

openssl pkcs12 -export -out "${name}.p12" -name "${name}" -inkey "${name}.key" -in "${name}.crt"
