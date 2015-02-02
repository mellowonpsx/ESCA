#!/bin/bash
cd /var/www/html/twitter-flume-sampling-module
#if agent is not started, start it again
flume_agent=$(ps aux | grep -v "grep" | grep -v "run.sh" | grep "flume" | grep "twitter-flume-sampling-module")
if [ -z "$flume_agent" ] ; then
    echo "not running, start flume"
    sudo -u cloudera nohup flume-ng agent --name TwitterAgent --conf . --conf-file flume.conf -Dflume.root.logger=INFO,console &
else
    echo "flume already running: $flume_agent"
fi
