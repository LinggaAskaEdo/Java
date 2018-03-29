#!/bin/sh

fname='lib/invoice.jar'
echo $(readlink -f "$fname")
java -jar $(readlink -f "$fname")