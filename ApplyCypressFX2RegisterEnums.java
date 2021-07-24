//Applies enum types to Cypress FX2 registers.
//@author Graham Sutherland
//@category Processor.CypressFX2
//@keybinding 
//@menupath 
//@toolbar 

import java.util.Stack;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.util.*;
import ghidra.program.model.reloc.*;
import ghidra.program.model.data.*;
import ghidra.program.model.block.*;
import ghidra.program.model.symbol.*;
import ghidra.program.model.scalar.*;
import ghidra.program.model.mem.*;
import ghidra.program.model.listing.*;
import ghidra.program.model.lang.*;
import ghidra.program.model.pcode.*;
import ghidra.program.model.address.*;
import ghidra.program.model.address.Address;

public class ApplyCypressFX2RegisterEnums extends GhidraScript
{

	@Override
	public void run() throws Exception
	{
		if (currentProgram == null)
		{
			println("NO CURRENT PROGRAM");
			return;
		}
		
		DataTypeManager dtm = currentProgram.getDataTypeManager();
		
		// add all enums to type info collection
		List<ghidra.program.model.data.Enum> fx2enums = new ArrayList<ghidra.program.model.data.Enum>();
		Iterator<DataType> types = dtm.getAllDataTypes();
		while (types.hasNext())
		{
			DataType dataType = types.next();
			if (dataType.getName().startsWith("FX2_REG_BITS_") || dataType.getName().startsWith("FX2_REG_VAL_"))
			{
				if (dataType instanceof ghidra.program.model.data.Enum)
				{
					println(dataType.getName());
					fx2enums.add((ghidra.program.model.data.Enum)dataType);
				}
			}
		}
		println("Enum count: " + fx2enums.size());

		HashMap<String, EnumDataType> registerTypes = new HashMap<String, EnumDataType>();
		for (ghidra.program.model.data.Enum fx2enum : fx2enums)
		{
			EnumDataType edt = (EnumDataType)fx2enum.clone(dtm);
			if (fx2enum.getLength() != 1)
			{
				println("Fixing length on " + fx2enum.getName());
				edt.setLength(1);
			}
			fx2enum.replaceWith(edt);
			String regName = edt.getName().replace("FX2_REG_BITS_", "").replace("FX2_REG_VAL_", "");
			registerTypes.put(regName, edt);
			println("Register " + regName + " will be mapped to type " + fx2enum.getName());
		}

		List<AddressLabelInfo> symbols = currentProgram.getLanguage().getDefaultSymbols();
		for (AddressLabelInfo symbol : symbols)
		{
			if (registerTypes.containsKey(symbol.getLabel()))
			{
				EnumDataType registerType = registerTypes.get(symbol.getLabel());
				println("Symbol " + symbol.getLabel() + " @ " + symbol.getAddress().toString() + " being mapped to type " + registerType.getName());
				
				if (getDataAt(symbol.getAddress()) != null)
					removeDataAt(symbol.getAddress());
				createData(symbol.getAddress(), registerType);
			}
		}
		
		println("Done.");
	}
}
