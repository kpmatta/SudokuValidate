# Sudoku Validator

This is to parse the input csv file into 9x9 grid
and validates the numbers for Sudoku puzzle in the grid.

-   If the given puzzle is valid, return exit code as 0
-   If the given puzzle is invalid, return the appropriate exit code.
-   exit code for exception is -1.


## Notes and Assumptions
- Input puzzle can be filled partially or fully.
- CSV reader default charset is "utf-8".
- CSV reader default delimiter is comma.
- Blank or no space for missing number in the csv file.
- Missing number parsed as '\0' char to the 2D array.
- Only numbers between 1 and 9 are allowed in the csv file.

## Run the application

    validate.bat  /csv/file/path

    OR 
    
    java -cp ./target/sudoku-validate-1.0.jar org.krishnamatta.sudokuvalidate.Validate  src/test/resources/input/SampleErrorFile.csv

    ExitCode on Windows: 
        echo Exit Code : %ERRORLEVEL%

    ExitCode on Linux or Mac:
        echo Exit Code : $?

## Status codes
    0  - SUCCESS
    -1 - Exception
    1  - INVALID_INPUT_ARGUMENT
    2  - INVALID_ROW_COUNT
    3  - INVALID_COLUMN_COUNT
    4  - INVALID_NUMBER
    5  - DUPLICATE_NUMBER

## Example output status:
- VALID : StatusCode:0
- INVALID : Duplicate number 3 in row 9, StatusCode:5

## Build & Test
- mvn package
- mvn test

## Surefire report
- mvn surefire-report:report
