from dataclasses import dataclass, field
import random
import string
import json
from dict2xml import dict2xml

instructors = ["Smith", "Jones", "Genky", "Johnson", "Jefferson", "Markson", "Barbaux", "Roche", "Rivia", "Jackson",
               "Parker", "Noelle", "Leonidas", "Jenson", "Applesmith", "Jeger", "Von-Weimer", "Paulchel", "Nyabuto",
               "Usher", "Woodhead", "Jethro", "Gundrum", "Malish", "Langley", "McDonald", "Donavan", "Hannon", "Chung"]
departments = {"CIS": "Computer Science", "EEC": "Electrical Engineering", "ENG": "English", "MTH": "Mathematics",
               "CVE": "Civil Engineering", "BUS": "Business", "PSY": "Psychology", "UST": "Urban Studies",
               "IST": "Information Studies", "LAW": "Law", "CHE": "Chemical Engineering", "HSC": "Health Science",
               "HIS": "History", "ART": "Arts and Humanities", "BIO": "Biology", "CHM": "Chemistry"}
catalogue = {}


def random_seat_number() -> int:
    return random.randint(20, 50)


def random_id() -> str:
    ID = str(random.randint(100, 999))
    return ID


def check_courses(course_name) -> bool:
    global all_courses
    if course_name in all_courses.catalog.keys():
        return True
    # for course in all_courses.catalog:
    #     if course_name == course.course_name:
    #         return True
    return False


@dataclass()
class Course:
    course_name: str
    department: str
    instructor: str
    max_seats: int = field(default_factory=int, init=False, )
    __id: str = field(default_factory=str, init=False, )
    students: int = field(default_factory=int, init=False)

    def __str__(self) -> str:
        return f"Course Name: {self.course_name}\nDepartment: {self.department}\nInstructor: {self.instructor}\n"

    def set_seats(self) -> None:
        self.max_seats = random_seat_number()

    def generate_id(self) -> None:
        self.__id = random_id()
        self.course_name = self.course_name[:3] + " " + self.__id

    def fill_seats(self, max_seats):
        self.students = random.randint(0, max_seats)

    def __post_init__(self):
        self.set_seats()
        self.generate_id()
        while check_courses(self.course_name):
            self.generate_id()
        self.fill_seats(self.max_seats)


@dataclass()
class CourseList:
    catalog: dict = field(default_factory=dict, init=False)

    def __str__(self) -> str:
        buffer = "\nAll courses:\n\n"
        for course in self.catalog:
            buffer += str(self.catalog[course]) + "\n"
        return buffer

    def save_to_json(self) -> None:
        with open('coursecatalog.json', 'w') as jfile:
            json.dump(self.catalog, jfile, indent=4)

    def save_to_xml(self) -> None:
        xdata = dict2xml(self.catalog)
        with open('coursecatalog.xml', 'w') as xfile:
            xfile.write(xdata)


def randomInstructor() -> str:
    global instructors
    index = random.randint(0, len(instructors) - 1)
    return instructors[index]


def generateCourse(courses_per_dept):
    global departments, all_courses
    for tag in departments:
        for i in range(courses_per_dept):
            course = Course(course_name=tag, department=departments[tag], instructor=randomInstructor())
            all_courses.catalog[course.course_name] = {'course name': course.course_name,
                                                       'department': course.department,
                                                       'instructor': course.instructor,
                                                       'students': course.students,
                                                       'maximum capacity': course.max_seats,
                                                       'available seats': course.max_seats - course.students}


all_courses = CourseList()
generateCourse(200)

all_courses.save_to_xml()

print(all_courses)
print(all_courses.catalog)
