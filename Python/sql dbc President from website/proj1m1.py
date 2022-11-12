from lxml import html
import requests
import pyodbc

# access database here:
conn = pyodbc.connect(
            'Driver={SQL Server};' +
            ('SERVER={THE-BEAST\MSSQLSERVER01};' +
             'DATABASE={President};' +
             'TRUSTED_CONNECTION=yes')
        )
cursor = conn.cursor()
#cursor.execute('INSERT INTO Test values(\'from\', 25, \'python\')')
#conn.commit()


def addtodb(pname, sdate, link, tpath):
    # print(pname, sdate, link, tpath)

    cursor.execute('INSERT INTO Speeches(Name_Of_President, Date_Of_Union_Address, Link_To_Address, Text_Of_Address)\n'
                   + 'SELECT \'' + pname + '\' as Name_Of_President, \'' + sdate + '\' as Date_Of_Union_Address, '
                   + '\'' + link + '\' as Link_To_Address, \n'
                   + '* FROM OPENROWSET(BULK N\'' + tpath + '\', SINGLE_BLOB) AS Document;')
# cursor.execute('SELECT * FROM Test')
# success in connecting to database

mainURL = 'https://www.infoplease.com'
mainPage = requests.get('https://www.infoplease.com/primary-sources/government'
                        '/presidential-speeches/state-union-addresses')
# print(str(mainPage.url))
mainTree = html.fromstring(mainPage.content)
tabName = mainTree.xpath('//h1/span/text()')

# next I would take that title and see if there is a table with that name, and if not, create it

# these next lines will grab the subject and link for each article on the page
articleLinks = mainTree.xpath('//span[@class= "article"]/a/@href')
# print(articleLinks) # this contains relative URL, need to make it full URL

# required info is Pres name, Date, Link and the full text

# this for block will iterate through each link, I noticed some issues on the website
# I will be using "primary-sources" as a string comparator because a handful of links do not
# do anything or go to the wrong page.

for i in range(len(articleLinks)):
    tempURL = articleLinks[i]
    if ("primary-sources" in tempURL):
        tempURL = mainURL + tempURL
        # get each individual page info, update txt files:
        articlePage = requests.get(tempURL)
        articleTree = html.fromstring(articlePage.content)
        # Creation of text for .txt file:
        articleText = articleTree.xpath('//div[@class= "article"]/p/text()')
        textOfAddress = ("\n".join([j.strip() for j in articleText]))
        # Creation of title:
        articleTitle = articleTree.xpath('//h1/span/text()')
        title = articleTitle[0].partition("State of the Union Address:")
        if title[0] == '':
            title = title[2].strip()
        else:
            title = title[0]
        # Curation of .txt files:
        f = open('addressTexts/' + title + '.txt', 'w+')
        f.write(textOfAddress)

        # Split President name and date:
        presName = title.partition("(")
        date = presName[2].partition(")")
        date = date[0]
        presName = presName[0]
        # store all the info into the db table:
        path = 'D:\Spring 22\Big Data\project1/' + f.name
        f.close()
        addtodb(presName, date, tempURL, path)

conn.commit()
conn.close()
print("All values added to DB successfully")
