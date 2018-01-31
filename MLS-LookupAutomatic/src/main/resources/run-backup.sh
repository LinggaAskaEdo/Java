#!/bin/bash
echo "Try backup Cell DB, please wait..."

mysql -uroot -e "USE MLS_CELL_LOOKUP_BACKUP; TRUNCATE TABLE CELL_DB; INSERT INTO CELL_DB (radio, mcc, net, area, cell, unit, lon, lat, ranges, samples, changeable, created, updated, average_signal) SELECT radio, mcc, net, area, cell, unit, lon, lat, ranges, samples, changeable, created, updated, average_signal FROM MLS_CELL_LOOKUP.CELL_DB;"

echo "Backup completed..."
