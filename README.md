# Ghidra Cypress FX2 processor definition
Ghidra processor definition for Cypress FX2, an 8051-based USB controller.

All register definitions were created to match the "EZ-USB FX2 Technical Reference Manual" document from Cypress.

Place cypress_fx2.pspec in Ghidra/Processors/8051/data/languages/

## Optional - Adding register enums

Adding register enums makes a major difference to the readability of code, since you get the names of all the bits.

Load CypressFX2.h using File -> Parse C Header. There are two ways of loading it:

- In the UI, save one of the existing prf profiles with a new name, delete all the files, clear the Parse Options, then add just the CypressFX2.h file.
- Go to %userprofile%/.ghidra/parserprofiles/ and create a file called CypressFX2.prf, then write the path into it. In the Parse C Header window, click "Refresh User Profiles", then use the one you created.

Either method should load the enum types into your program's data type manager.

Next, run the provided ApplyCypressFX2RegisterEnums.java script. This will repack the enums to 1 byte (currently there's no way to specify enum sizes in .h imports, so they default to 4 bytes) and automatically apply all the types to the pre-defined register symbols.

## Current status

The following registers are covered:

| Register (Address)              | Included in pspec? | Included in header? |
| ------------------------------- | ------------------ | ------------------- |
| CPUCS (0xE600)                  | Yes                | Yes                 |
| IFCONFIG (0xE601)               | Yes                | Yes                 |
| PINFLAGSAB (0xE602)             | Yes                | Yes                 |
| PINFLAGSCD (0xE603)             | Yes                | Yes                 |
| FIFORESET (0xE604)              | Yes                | Yes                 |
| BREAKPT (0xE605)                | Yes                | Yes                 |
| BPADDRH (0xE606)                | Yes                | N/A                 |
| BPADDRL (0xE607)                | Yes                | N/A                 |
| UART230 (0xE608)                | Yes                | Yes                 |
| FIFOPINPOLAR (0xE609)           | Yes                | Yes                 |
| REVID (0xE60A)                  | Yes                | N/A                 |
| REVCTL (0xE60B)                 | Yes                | Yes                 |
| GPIFHOLDTIME (0xE60C)           | Yes                | Yes                 |
| EP1OUTCFG (0xE610)              | Yes                | Yes                 |
| EP1INCFG (0xE611)               | Yes                | Yes                 |
| EP2CFG (0xE612)                 | Yes                | Yes                 |
| EP4CFG (0xE613)                 | Yes                | Yes                 |
| EP6CFG (0xE614)                 | Yes                | Yes                 |
| EP8CFG (0xE615)                 | Yes                | Yes                 |
| EP2FIFOCFG (0xE618)             | Yes                | Yes                 |
| EP4FIFOCFG (0xE619)             | Yes                | Yes                 |
| EP6FIFOCFG (0xE61A)             | Yes                | Yes                 |
| EP8FIFOCFG (0xE61B)             | Yes                | Yes                 |
| EP2AUTOINLENH (0xE620)          | Yes                | N/A                 |
| EP2AUTOINLENL (0xE621)          | Yes                | N/A                 |
| EP4AUTOINLENH (0xE622)          | Yes                | N/A                 |
| EP4AUTOINLENL (0xE623)          | Yes                | N/A                 |
| EP6AUTOINLENH (0xE624)          | Yes                | N/A                 |
| EP6AUTOINLENL (0xE625)          | Yes                | N/A                 |
| EP8AUTOINLENH (0xE626)          | Yes                | N/A                 |
| EP8AUTOINLENL (0xE627)          | Yes                | N/A                 |
| EP2FIFOPFH (0xE630)             | Yes                | Yes                 |
| EP2FIFOPFL (0xE631)             | Yes                | N/A                 |
| EP4FIFOPFH (0xE632)             | Yes                | Yes                 |
| EP4FIFOPFL (0xE633)             | Yes                | N/A                 |
| EP6FIFOPFH (0xE634)             | Yes                | Yes                 |
| EP6FIFOPFL (0xE635)             | Yes                | N/A                 |
| EP8FIFOPFH (0xE636)             | Yes                | Yes                 |
| EP8FIFOPFL (0xE637)             | Yes                | N/A                 |
| EP2ISOINPKTS (0xE640)           | Yes                | Yes                 |
| EP4ISOINPKTS (0xE641)           | Yes                | Yes                 |
| EP6ISOINPKTS (0xE642)           | Yes                | Yes                 |
| EP8ISOINPKTS (0xE643)           | Yes                | Yes                 |
| INPKTEND (0xE648)               | Yes                | Yes                 |
| OUTPKTEND0 (0xE649)             | Yes                | Yes                 |
| EP2FIFOIE (0xE650)              | Yes                | No                  |
| EP2FIFOIRQ (0xE651)             | Yes                | No                  |
| EP4FIFOIE (0xE652)              | Yes                | No                  |
| EP4FIFOIRQ (0xE653)             | Yes                | No                  |
| EP6FIFOIE (0xE654)              | Yes                | No                  |
| EP6FIFOIRQ (0xE655)             | Yes                | No                  |
| EP8FIFOIE (0xE656)              | Yes                | No                  |
| EP8FIFOIRQ (0xE657)             | Yes                | No                  |
| IBNIE (0xE658)                  | Yes                | No                  |
| IBNIRQ (0xE659)                 | Yes                | No                  |
| NAKIE (0xE65A)                  | Yes                | No                  |
| NAKIRQ (0xE65B)                 | Yes                | No                  |
| USBIE (0xE65C)                  | Yes                | No                  |
| USBIRQ (0xE65D)                 | Yes                | No                  |
| EPIE (0xE65E)                   | Yes                | No                  |
| EPIRQ (0xE65F)                  | Yes                | No                  |
| GPIFIE (0xE660)                 | Yes                | No                  |
| GPIFIRQ (0xE661)                | Yes                | No                  |
| USBERRIE (0xE662)               | Yes                | No                  |
| USBERRIRQ (0xE663)              | Yes                | No                  |
| ERRCNTLIM (0xE664)              | Yes                | No                  |
| CLRERRCNT (0xE665)              | Yes                | No                  |
| INT2IVEC (0xE666)               | Yes                | No                  |
| INT4IVEC (0xE667)               | Yes                | No                  |
| INTSETUP (0xE668)               | Yes                | No                  |
| PORTACFG (0xE670)               | Yes                | No                  |
| PORTCCFG (0xE671)               | Yes                | No                  |
| PORTECFG (0xE672)               | Yes                | No                  |
| I2CS (0xE678)                   | Yes                | No                  |
| I2DAT (0xE679)                  | Yes                | No                  |
| I2CTL (0xE67A)                  | Yes                | No                  |
| XAUTODAT1 (0xE67B)              | Yes                | No                  |
| XAUTODAT2 (0xE67C)              | Yes                | No                  |
| UDMACRCH (0xE67D)               | Yes                | No                  |
| UDMACRCL (0xE67E)               | Yes                | No                  |
| UDMACRCQUALIFIER (0xE67F)       | Yes                | No                  |
| USBCS (0xE680)                  | Yes                | No                  |
| SUSPEND (0xE681)                | Yes                | No                  |
| WAKEUPCS (0xE682)               | Yes                | No                  |
| TOGCTL (0xE683)                 | Yes                | No                  |
| USBFRAMEH (0xE684)              | Yes                | No                  |
| USBFRAMEL (0xE685)              | Yes                | No                  |
| MICROFRAME (0xE686)             | Yes                | No                  |
| FNADDR (0xE687)                 | Yes                | No                  |
| EP0BCH (0xE68A)                 | Yes                | No                  |
| EP0BCL (0xE68B)                 | Yes                | No                  |
| EP1OUTBC (0xE68D)               | Yes                | No                  |
| EP1INBC (0xE68F)                | Yes                | No                  |
| EP2BCH (0xE690)                 | Yes                | No                  |
| EP2BCL (0xE691)                 | Yes                | No                  |
| EP4BCH (0xE694)                 | Yes                | No                  |
| EP4BCL (0xE695)                 | Yes                | No                  |
| EP6BCH (0xE698)                 | Yes                | No                  |
| EP6BCL (0xE699)                 | Yes                | No                  |
| EP8BCH (0xE69C)                 | Yes                | No                  |
| EP8BCL (0xE69D)                 | Yes                | No                  |
| EP0CS (0xE6A0)                  | Yes                | No                  |
| EP1OUTCS (0xE6A1)               | Yes                | No                  |
| EP1INCS (0xE6A2)                | Yes                | No                  |
| EP2CS (0xE6A3)                  | Yes                | No                  |
| EP4CS (0xE6A4)                  | Yes                | No                  |
| EP6CS (0xE6A5)                  | Yes                | No                  |
| EP8CS (0xE6A6)                  | Yes                | No                  |
| EP2FIFOFLGS (0xE6A7)            | Yes                | No                  |
| EP4FIFOFLGS (0xE6A8)            | Yes                | No                  |
| EP6FIFOFLGS (0xE6A9)            | Yes                | No                  |
| EP8FIFOFLGS (0xE6AA)            | Yes                | No                  |
| EP2FIFOBCH (0xE6AB)             | Yes                | No                  |
| EP2FIFOBCL (0xE6AC)             | Yes                | No                  |
| EP4FIFOBCH (0xE6AD)             | Yes                | No                  |
| EP4FIFOBCL (0xE6AE)             | Yes                | No                  |
| EP6FIFOBCH (0xE6AF)             | Yes                | No                  |
| EP6FIFOBCL (0xE6B0)             | Yes                | No                  |
| EP8FIFOBCH (0xE6B1)             | Yes                | No                  |
| EP8FIFOBCL (0xE6B2)             | Yes                | No                  |
| SUDPTRH (0xE6B3)                | Yes                | No                  |
| SUDPTRL (0xE6B4)                | Yes                | No                  |
| SUDPTRCTL (0xE6B5)              | Yes                | No                  |
| SETUPDAT_bmRequestType (0xE6B8) | Yes                | No                  |
| SETUPDAT_bmRequest (0xE6B9)     | Yes                | No                  |
| SETUPDAT_wValueH (0xE6BA)       | Yes                | No                  |
| SETUPDAT_wValueL (0xE6BB)       | Yes                | No                  |
| SETUPDAT_wIndexH (0xE6BC)       | Yes                | No                  |
| SETUPDAT_wIndexL (0xE6BD)       | Yes                | No                  |
| SETUPDAT_wLengthH (0xE6BE)      | Yes                | No                  |
| SETUPDAT_wLengthL (0xE6BF)      | Yes                | No                  |
| GPIFWFSELECT (0xE6C0)           | Yes                | No                  |
| GPIFIDLECS (0xE6C1)             | Yes                | No                  |
| GPIFIDLECTL (0xE6C2)            | Yes                | No                  |
| GPIFCTLCFG (0xE6C3)             | Yes                | No                  |
| GPIFADRH (0xE6C4)               | Yes                | No                  |
| GPIFADRL (0xE6C5)               | Yes                | No                  |
| FLOWSTATE (0xE6C6)              | Yes                | No                  |
| FLOWLOGIC (0xE6C7)              | Yes                | No                  |
| FLOWEQ0CTL (0xE6C8)             | Yes                | No                  |
| FLOWEQ1CTL (0xE6C9)             | Yes                | No                  |
| FLOWHOLDOFF (0xE6CA)            | Yes                | No                  |
| FLOWSTB (0xE6CB)                | Yes                | No                  |
| FLOWSTBEDGE (0xE6CC)            | Yes                | No                  |
| FLOWSTBPERIOD (0xE6CD)          | Yes                | No                  |
| GPIFTCB3 (0xE6CE)               | Yes                | No                  |
| GPIFTCB2 (0xE6CF)               | Yes                | No                  |
| GPIFTCB1 (0xE6D0)               | Yes                | No                  |
| GPIFTCB0 (0xE6D1)               | Yes                | No                  |
| EP2GPIFFLGSEL (0xE6D2)          | Yes                | No                  |
| EP2GPIFPFSTOP (0xE6D3)          | Yes                | No                  |
| EP2GPIFTRIG (0xE6D4)            | Yes                | No                  |
| EP4GPIFFLGSEL (0xE6DA)          | Yes                | No                  |
| EP4GPIFPFSTOP (0xE6DB)          | Yes                | No                  |
| EP4GPIFTRIG (0xE6DC)            | Yes                | No                  |
| EP6GPIFFLGSEL (0xE6E2)          | Yes                | No                  |
| EP6GPIFPFSTOP (0xE6E3)          | Yes                | No                  |
| EP6GPIFTRIG (0xE6E4)            | Yes                | No                  |
| EP8GPIFFLGSEL (0xE6EA)          | Yes                | No                  |
| EP8GPIFPFSTOP (0xE6EB)          | Yes                | No                  |
| EP8GPIFTRIG (0xE6EC)            | Yes                | No                  |
| XGPIFSGLDATH (0xE6F0)           | Yes                | No                  |
| XGPIFSGLDATLX (0xE6F1)          | Yes                | No                  |
| XGPOIFSGLDATLNOX (0xE6F2)       | Yes                | No                  |
| GPIFREADYCFG (0xE6F3)           | Yes                | No                  |
| GPIFREADYSTAT (0xE6F4)          | Yes                | No                  |
| GPIFABORT (0xE6F5)              | Yes                | No                  |

Registers marked N/A do not contain any bits that can be usefully represented by an enum. These are usually registers that contain addresses or other values.

Registers marked "no" are on the todo list.