import string
from lxml import html
import requests
import pyodbc
import random
import string
from dataclasses import dataclass, field
import re


# def id_gen(job) -> str:
#     """INTERNAL USE: generates a unique and random id for each job and stores id with url in a dict"""
#     global id_dict
#     letters = ''.join(random.choices(string.ascii_letters, k=15))
#     numbers = ''.join(random.choices(string.digits, k=15))
#     id = ''.join(random.choices(letters + numbers, k=15))
#     # print(id)
#     # print(id in id_dict.keys())
#     while id in id_dict.keys():
#         print('generating id')
#         letters = ''.join(random.choices(string.ascii_letters, k=15))
#         numbers = ''.join(random.choices(string.digits, k=15))
#         id = ''.join(random.choices(letters + numbers, k=15))
#     id_dict[id]=job
#     return id

@dataclass()
class Job:
    """parses job data to pass to xml file"""
    _title: str
    _company: str
    _url: str = field(repr=False)
    _city: str = field(init=False)
    _remote: bool = field(init=False, repr=False)
    __keywords: list = field(default_factory=list, init=False, repr=True)

    """job_object METHODS"""

    def __post_init__(self) -> None:
        self._city = "Not listed"
        if not re.search('[a-zA-Z]', self._title):
            self._title = "Not listed"
        if not re.search('[a-zA-Z]', self._company):
            self._company = "Not listed"
        # self.__id = id_gen(self._url)

        # title words will be put into keywords
        for word in self._title.split():
            self.__keywords.insert(0, word)

    def __str__(self):
        return f"{self._title} at {self._company}, out of {self._city}, keywords: {self.__keywords}"

    def get_title(self) ->str:
        return self._title

    def set_city(self, city:str) -> None:
        if not re.search('[a-zA-Z]', city):
            self._city = "Not listed or remote"
        else:
            self._city = city

    def get_city(self) -> str:
        return self._city

    def set_remote(self, yar:bool) -> None:
        self._remote = yar

    def put_in_db(self) -> None:
        print("Database not implemented yet \nURL:")
        print(f"{self.__url}")

    def set_url(self, url):
        self.__url = url


def get_job_info(job_url: str) -> None:
    """Pull and organize individual Job info"""
    global job_count
    job_page = requests.get(job_url)
    job_tree = html.fromstring(job_page.content)
    title = ''.join(job_tree.xpath("//h1/text()"))
    company = ''.join(job_tree.xpath("//div[@class='jobsearch-CompanyInfoContainer']//a/text()"))
    """Depending on in depth, can change OH to a variable based on user input"""
    city = ''.join(job_tree.xpath("//div[@class='jobsearch-CompanyInfoContainer']//div/text()[contains(.,'OH')]"))
    job = Job(_title=title, _company=company, _url=job_url)
    job.set_city(city)
    if "Not listed" in job.get_title():
        print('Job page-request denied, aborting job creation')
        return
    print(str(job), job_url)
    job_count += 1


def get_next_page() -> str:
    """Gets the next page's relative url and concats it to the home url"""
    global page_count
    try:
        next_link = page_tree.xpath("//li/a[contains(@aria-label, 'Next')]/@href")
        print(next_link)
        next_url = indeed_home_url + ''.join(next_link)
        print('next URL: ' + next_url)
        page_count += 1
        return next_url
    except Exception as e:
        print(str(e))
        return "$$$"


def pull_jobs() -> None:
    """pull all job relative urls from page concat to home url"""
    try:
        jobs = page_tree.xpath("//div[@class='mosaic-zone']/div/a/@href")
        for link in jobs:
            job_link = indeed_home_url + ''.join(link)
            get_job_info(job_link)
    except Exception as e:
        print(str(e))
#    for link in jobs:
#    print(link)

id_dict={}
job_count = 0
page_count = 0
indeed_home_url = "https://www.indeed.com"
cle_comp_sci_url = "https://www.indeed.com/jobs?l=Cleveland,%20OH&radius=25"
current_page = requests.get(cle_comp_sci_url)
page_tree = html.fromstring(current_page.content)
current_url = ''

while page_count < 500:
    pull_jobs()
    current_url = get_next_page()
    current_page = requests.get(current_url)
    page_tree = html.fromstring(current_page.content)
    if job_count > 100:
        break

print(str(job_count), " jobs")
