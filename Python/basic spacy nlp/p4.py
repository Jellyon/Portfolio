import spacy
import string
from bs4 import BeautifulSoup
import requests
from lxml import html
dictionary1 = {}
dictionary2 = {}
dictionary3 = {}
dictionary4 = {}
dictionary5 = {}
dictionary6 = {}
nlp = spacy.load('en_core_web_sm')
vectors = ['research', 'data', 'mining', 'data_mining', 'machine_learning', 'deep_learning']


def countStuff(token, dictionary):
    token = "".join(token.strip())
    if string.punctuation.__contains__(token):
        return
    if token.__contains__('_'):
        if token.__contains__('data'):
            token = 'data_mining'
        elif token.__contains__('machine'):
            token = 'machine_learning'
        elif token.__contains__('deep'):
            token = 'deep_learning'
        else:
            return
    if token == '':
        return
    elif token not in dictionary:
        dictionary[token] = 1
    else:
        dictionary[token] += 1


def openFile(path, dictionary):
    with open(path, "rb") as url:
        soup = BeautifulSoup(url, features="lxml")
        doc = nlp((soup.get_text().replace('\n', ' ').replace('_', ' ').lower().replace('data mining', 'data_mining').replace('machine learning', 'machine_learning').replace('deep learning', 'deep_learning')))
        for token in doc:
            if string.punctuation.__contains__(token.text):
                continue
            elif token.text.__contains__('_'):
                countStuff(token.text, dictionary)
            else:
                countStuff(token.lemma_.lower(), dictionary)

openFile(r"wikidocs/doc1.txt", dictionary1)
openFile(r"wikidocs/doc2.txt", dictionary2)
openFile(r"wikidocs/doc3.txt", dictionary3)
openFile(r"wikidocs/doc4.txt", dictionary4)
openFile(r"wikidocs/doc5.txt", dictionary5)
openFile(r"wikidocs/doc6.txt", dictionary6)

print('document 1:')
for i in vectors:
    try:
        print(i + ' occurred: ' + str(dictionary1[i]) + ' times')
    except KeyError:
        print(i + ' occurred: 0 times')
print('\ndocument 2:')
for i in vectors:
    try:
        print(i + ' occurred: ' + str(dictionary2[i]) + ' times')
    except KeyError:
        print(i + ' occurred: 0 times')
print('\ndocument 3:')
for i in vectors:
    try:
        print(i + ' occurred: ' + str(dictionary3[i]) + ' times')
    except KeyError:
        print(i + ' occurred: 0 times')
print('\ndocument 4:')
for i in vectors:
    try:
        print(i + ' occurred: ' + str(dictionary4[i]) + ' times')
    except KeyError:
        print(i + ' occurred: 0 times')
print('\ndocument 5:')
for i in vectors:
    try:
        print(i + ' occurred: ' + str(dictionary5[i]) + ' times')
    except KeyError:
        print(i + ' occurred: 0 times')
print('\ndocument 6:')
for i in vectors:
    try:
        print(i + ' occurred: ' + str(dictionary6[i]) + ' times')
    except KeyError:
        print(i + ' occurred: 0 times')

# print(dictionary1)
# print(dictionary2)
# print(dictionary3)
# print(dictionary4)
# print(dictionary5)
# print(dictionary6)





