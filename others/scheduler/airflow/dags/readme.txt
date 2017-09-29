# Apache Airflow sample
# see http://pythonhosted.org/airflow/start.html

# Install Airflow
export AIRFLOW_HOME=~/airflow
pip install airflow
airflow initdb

airflow webserver -p 8090

python airflow_hello.py

airflow list_dags
airflow test hello hello_jane 2017-9-29

airflow backfill hello -s 2017-9-29 -e 2017-9-30
