# Ghidra Cypress FX2 processor definition
Ghidra processor definition for Cypress FX2, an 8051-based USB controller.

Place cypress_fx2.pspec in Ghidra/Processors/8051/data/languages/

## Optional - Adding register enums

Adding register enums makes a major difference to the readability of code, since you get the names of all the bits.

Load CypressFX2.h using File -> Parse C Header. There are two ways of loading it:

- In the UI, save one of the existing prf profiles with a new name, delete all the files, clear the Parse Options, then add just the CypressFX2.h file.
- Go to %userprofile%/.ghidra/parserprofiles/ and create a file called CypressFX2.prf, then write the path into it. In the Parse C Header window, click "Refresh User Profiles", then use the one you created.

This should load the enum types into your program.

Next, run the provided ApplyCypressFX2RegisterEnums.java script. This will repack the enums to 1 byte (currently there's no way to do this on .h imports) and apply all the types to the defined registers automatically.
