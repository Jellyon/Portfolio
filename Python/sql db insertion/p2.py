import json
import csv
import pyodbc
from bs4 import BeautifulSoup

conn = pyodbc.connect(
            'Driver={SQL Server};' +
            ('SERVER={THE-BEAST\MSSQLSERVER01};' +
             'DATABASE={BUSINESSES};' +
             'TRUSTED_CONNECTION=yes')
        )
cursor = conn.cursor()


def create_table(t_name, data):
    try:
        query = 'CREATE TABLE ' + t_name + '(\n'
        a = data[t_name]
        i = 0
        for b in a:
            c = b.keys()
        for d in c:
            if i:
                query += ',\n'
            query += '\"' + d + '\"' + ' varchar(50)'
            if not i:
                query += ' NOT NULL PRIMARY KEY'
            i += 1
        query += '\n);'
        cursor.execute(query)
#        print(query)
        conn.commit()
    except:
        print('sql error occurred')

def addrow(path_csvfile, tblname):
#     just throw the bulk insert sql code here
    query = 'BULK INSTERT ' + tblname + '\n' + \
            'FROM ' + path_csvfile + '\n' + 'WITH(\n' + \
            'FIELDTERMINATOR = \'\',\n' + \
            'ROWTERMINATOR = \'\\n\')'
#    print(query)
    cursor.execute(query)
#     with open(path_csvfile, 'r') as csvfile:
#         csv_reader = csv.DictReader(csvfile)
#     print(csv_reader)
#     for metric in csv_reader:
#         print(','.join(metric))

def parse_json(json_data, key):
    main_field = json_data[key]
#   This for loop gets each business's info and saves it into a csv file
    for data_type in main_field:
        #print(key)
        metric_list = data_type.keys()
        save_name = 'yelp/csv/' + data_type.get('business_id') + '.csv'
#   Writing into a file, if it doesn't exist, it will create it
        with open(save_name, 'w+') as csvfile:
            writer = csv.DictWriter(csvfile, fieldnames=metric_list)
            writer.writeheader()
            writer.writerow(data_type)
            addrow(save_name, key)
#         for metric in metric_list:
#             #print(metric, data_type.get(metric))
#             exit()
#             if isinstance(data_type[metric], dict):
#
#                 secondary_metric_list = data_type[metric].keys()
#                 for sec_metric in secondary_metric_list:
# #                    print(sec_metric)
#                     if isinstance(data_type[metric][sec_metric], dict):
#                         tertiary_metric_list = data_type[metric][sec_metric].keys()
#                         #print(tertiary_metric_list)
#                         for ter_metric in tertiary_metric_list:
#                            #print(ter_metric)
#                             continue


def get_file(f):
    try:
        j = open(f, 'r')
    except FileNotFoundError:
        print('file not found')
    return j


keys =[]
jfile = get_file('yelp/business100ValidForm.json')
jdata = json.load(jfile)
#print(jdata.keys())
for key in jdata.keys():
#    c_db(key)
    keys.append(key)
#print(keys[0])

for k in keys:
#    print(k)
    create_table(k, jdata)
    parse_json(jdata, k)
conn.close()