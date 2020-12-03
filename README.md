# Cell Phone Usage Report Initial Spec
Write a report for cell phone usages in the company for a given year, and print the report to your local printer.

### Data Model
Database tables are in comma separated files with the header in the first row. 

**CellPhone.csv**
*	employeeId
*	employeeName
*	purchaseDate
*	model

**CellPhoneUsageByMonth.csv** (beware that there may be more than one record for an employee on a single date, so it is not a perfect data in a perfect world)
*	employeeId
*	year
*	month
*	minutesUsed
*	dataUsed

### Report  

The report should contain the following information

**Header Section**

*	Report Run Date
*	Number of Phones
*	Total Minutes
*	Total Data
*	Average Minutes
*	Average Data

**Details Section**

For each company cell phone provide the following information
*	Employee Id
*	Employee Name
*	Model
*	Purchase Date
*	Minutes Usage
    *	one column for each month
*	Data Usage
    *	one column for each month

Report should be printed to a local printer in your computer. 

# Design Decisions
I opted to use OpenCSV for reading from and writing to the designated CSV files. This, of course, was assuming that writing to a CSV file was an acceptable result of executing the program. 

From there, the next ambiguity was whether the CSV file names were to be static or read in at time of execution. I chose to have them be static, because that's just easier to deal with.

After that, I came to an ambiguity regarding headers for the monthly data and minutes. I decided I didn't want to map the values to dates and just output the data.

I realized that I'd probably want to match data to date string headers, so I had to gather the unique dates into a Set, move that Set into a List (ensuring uniqueness of date Strings), sort the list, and then append those date strings to the list of headers.

