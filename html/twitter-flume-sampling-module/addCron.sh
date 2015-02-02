#!/bin/bash
cd /var/www/html/twitter-flume-sampling-module;
echo "before:";
sudo -u cloudera crontab -l 2>&1;
declare -i minutes;
minutes=$(date +%M)+2%60;
#i don't care if this work is already present, in case i replan it in 2 minutes (old version is erased)
(sudo -u cloudera crontab -l 2>&1 | grep -v "no crontab" | grep -v "/var/www/html/twitter-flume-sampling-module/run.sh"; echo "$minutes * * * * /var/www/html/twitter-flume-sampling-module/run.sh") 2>&1 | sort | uniq | sudo -u cloudera crontab -;
echo "after:";
sudo -u cloudera crontab -l 2>&1;



