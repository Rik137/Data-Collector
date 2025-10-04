# Metro Data Parser and File Processor

## Overview

This Java project provides a complete solution for parsing metro station data from web pages, JSON, and CSV files, processing it, and writing it back to disk in structured JSON format. It also supports searching for files in a folder hierarchy and combines data from multiple sources.

The project leverages:

- **Jsoup** for HTML parsing.
- **Jackson** for JSON parsing and serialization.
- Standard Java I/O for CSV processing and file operations.

---

## Features

1. **Web Page Parsing**
   - Fetches and saves HTML pages from a specified URL.
   - Extracts metro lines and stations, including transfer availability.

2. **File Search**
   - Recursive search for `.json` and `.csv` files.
   - Combines results for further processing.

3. **CSV and JSON Parsing**
   - Reads station data from multiple CSV and JSON files.
   - Handles various formats and missing data.

4. **Data Integration**
   - Merges station data from different sources.
   - Updates missing fields (`depth`, `date`, `transitions`, `line`) when available.

5. **Output**
   - Writes processed data into two JSON formats:
     1. Array of stations with detailed properties.
     2. Stations grouped by metro line number.

6. **Logging**
   - Configured using Log4j2 with separate files for errors and warnings.

---

## Project Structure  

├── src/  
│ ├── Main.java # Entry point  
│ ├── WebParser.java # HTML parsing logic  
│ ├── FolderSearch.java # Recursive file search  
│ ├── ParserFileCsv.java # CSV parsing  
│ ├── ParserFileJson.java # JSON parsing  
│ ├── CreatesAndWritesToDisk.java # Data integration and JSON output  
│ ├── Station.java # Station model  
│ ├── Line.java # Line model  
│ └── NotFoundFileJsonOrCsvException.java  
├── resources/  
│ ├── stations-data/ # Input folder with CSV and JSON data  
│ └── log4j2.xml # Logging configuration  
├── File.json # Output JSON file  
└── File1.json # Alternative output JSON file  

---

## Usage

1. **Clone the repository**:

```bash
git clone <repository_url>
cd <repository_folder>
Run the project:
javac -cp "libs/*" src/*.java
java -cp "libs/*:src" Main
Tasks performed by the program:
Parsing HTML web pages to extract metro lines and stations.
Searching folders for CSV and JSON files.
Parsing and printing CSV/JSON data.
Merging data from different sources.
Writing the consolidated data to JSON files.
JSON Output Examples
Format 1 – Array of stations:
{
  "stations": [
    {
      "name": "Station Name",
      "line": "1",
      "date": "2025-10-04",
      "depth": "12m",
      "hasConnection": true
    }
  ]
}
Format 2 – Grouped by line:
{
  "1": {
    "namberName": "1",
    "Stations": "Station Name"
  }
}
Dependencies
Jsoup – HTML parser.
Jackson Databind – JSON parser and serializer.
Java 11+ for file and date operations.
Logging
Logging is configured using Log4j2 (resources/log4j2.xml):
logs/errors.log – records error messages.
logs/queries.log – records warnings and info messages.
Exception Handling
NotFoundFileJsonOrCsvException is thrown if expected JSON or CSV files are missing or of an incorrect format.
