#!/usr/bin/env bash

# before launching you should install redoc-cli
# `npm install -g redoc-cli`

curl 127.0.0.1:8080/v2/api-docs --output swagger-docs.json && \
redoc-cli bundle swagger-docs.json -t ./api_doc_template/template.hbs --options.theme.spacing.sectionVertical=5 --options.noAutoAuth --title "ElectricityFarm API documentation" -o index.html

rm -rf swagger-docs.json