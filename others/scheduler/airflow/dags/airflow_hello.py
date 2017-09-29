from airflow import DAG
from airflow.operators.bash_operator import BashOperator
from datetime import datetime, timedelta

default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'start_date': datetime(2017, 9, 1),
    'retry_delay': timedelta(minutes=5),
    'priority_weight': 7,
}

dag = DAG('hello', default_args=default_args, schedule_interval=timedelta(1))

outDir = "/tmp/airflow/hello"
# t1, t2 and t3 are examples of tasks created by instantiating operators

t1 = BashOperator(
    task_id='hello_all',
    bash_command='echo Hello All! >> ' + outDir + "/hello.txt",
    retries=2,
    dag=dag)


t2 = BashOperator(
    task_id='hello_jane',
    bash_command='echo Hello Jane! >> ' + outDir + "/hello.txt",
    dag=dag)

t3 = BashOperator(
    task_id='hello_mary',
    bash_command='echo Hello Mary! >> ' + outDir + "/hello.txt",
    dag=dag)


t2.set_upstream(t1)
t3.set_upstream(t1)
