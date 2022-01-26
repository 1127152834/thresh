#!/usr/bin/env bash
npm run build:prod

docker build -t thresh-cloud-web .
