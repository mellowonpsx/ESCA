#!/bin/bash
cd /var/www/html/sentiment-analysis-module;
echo "before:";
sudo -u cloudera crontab -l 2>&1;
declare -i minutes;
minutes=$(date +%M)+2%60;
#i don't care if this work is already present, in case it will be re-planed it in 2 minutes (old version is erased)
(sudo -u cloudera crontab -l 2>&1 | grep -v "no crontab" | grep -v "/var/www/html/sentiment-analysis-module/run.sh"; echo "$minutes * * * * /var/www/html/sentiment-analysis-module/run.sh") 2>&1  | sort | uniq | sudo -u cloudera crontab -;
#remove this work:
#(sudo -u cloudera crontab -l 2>&1 | grep -v "no crontab" | grep -v "/var/www/html/sentiment-analysis-module/run.sh") 2>&1  | sort | uniq | sudo -u cloudera crontab -;
echo "after:";
sudo -u cloudera crontab -l 2>&1;



