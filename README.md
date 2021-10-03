# KIIT-SAP-Portal-Automation
## Introduction
KIIT Deemed University provides a [SAP Portal](https://kiitportal.kiituniversity.net/) to its students for all the academics and college related stuffs.<br><br>
Some facilities available in the SAP are :<br>
- Student Attendance Details<br>
- Fees Details<br>
- Demand Letter<br>
- Subject and Exam bookings<br>
- Grade Reports<br>
- Material/Resources<br>

## Rationale
I recently came across **Selenium**. Selenium is an open-source tool that automates web browsers. I wanted to try that out. So this project was meant for practice only.
<br>

## Implementation
In this project, **Java EE, Firefox** and **Selenium** have been used along with **Eclipse** and **Geckodriver**.<br>
1. In the beginning, a window pops up asking for the user's roll number and password to login.<br>
2. When the Submit button is clicked, a new folder "SAP Extract" is created (if not exists) at the desktop. <br>
3. Mozilla Firefox opens up and navigates to SAP Portal. <br>
4. It then tries to log in. If the credentials are incorrect, "Invalid Credentials" will get printed in the window.
5. Else, it will make a folder inside SAP Extract named by the roll name. <br>
6. It will then perform the following operations :
    - Make a Downloads folder inside the roll number directory. This folder is used when something is downloaded. The files are renamed and moved to their respective directories.
    - Fetch and extract the user's attendance details and place it in a folder called "Attendance Details". There will be an Excel sheet each for the semesters.
    - Fetch and extract the user's information and place it in a folder called "Sudent Details". The details will be stored in a text file.
    - Download the Demand letter and store it in a folder called "Demand Letter".
    - Download the grade reports and store it in a folder called "Grade Report". The files will be in PDF formats and named according to the semester.
    - At the end, the Downloads folder is deleted.
7. The status of each of this functionality (worked succesfully or failed) gets printed in the window displayed.

## Note
This project is not meant for any monetized activites. **Its for learning purposes only**. Feel free to use the code if needed for academic benefits only. <br>
The code was developed in September 2021. There maybe some changes in the portal after the development due to which the program may not work .

### Thanks & Regards <br>Swastik Sourav Sahoo
