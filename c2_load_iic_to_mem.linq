<Query Kind="Statements" />

/*
C2 Load IIC to Mem - Copyright (c) 2021 Graham Sutherland
This script turns C2 Load firmware images dumped from I2C flash into a memory image suitable for loading into Ghidra using the Cypress FX2 language (8051 variant).
*/

byte[] data = File.ReadAllBytes("input.iic");

if (data[0] != 0xC2)
{
	Console.WriteLine("This is not a C2 load image.");
}

Console.WriteLine($"VID: 0x{data[2]:X2}{data[1]:X2}");
Console.WriteLine($"PID: 0x{data[4]:X2}{data[3]:X2}");
Console.WriteLine($"DID: 0x{data[6]:X2}{data[5]:X2}");
Console.WriteLine($"Configuration: 0x{data[7]:X2}");
Console.WriteLine();

byte[] memory = new byte[0x2000];

int ofs = 8;
while (true)
{
	int blockLength = (data[ofs] << 8) + data[ofs + 1];
	int blockAddr = (data[ofs + 2] << 8) + data[ofs + 3];
	if (blockLength == 0x8001 && blockAddr == 0xE600)
	{
		// final block
		Console.WriteLine($"Found 0x8001E600 terminator at 0x{ofs:X4}.");
		break;
	}
	Console.WriteLine("Block:");
	Console.WriteLine($"\tDescriptor Offset = 0x{ofs:X4}");
	Console.WriteLine($"\tData Offset       = 0x{ofs + 4:X4}");
	Console.WriteLine($"\tLength            = 0x{blockLength:X4}");
	Console.WriteLine($"\tAddress           = 0x{blockAddr:X4}");
	Console.WriteLine();
	if (blockLength > 0x3FF)
	{
		Console.WriteLine("Error: Block length exceeded 0x3FF.");
		break;
	}
	if (blockAddr > 0x1FFF)
	{
		Console.WriteLine("Error: Block address exceeded 0x1FFF.");
		break;
	}
	if (blockAddr + blockLength > 0x1FFF)
	{
		Console.WriteLine("Error: Block length at given address would write past the end of memory.");
		break;
	}
	Array.Copy(data, ofs + 4, memory, blockAddr, blockLength);
	ofs += 4;
	ofs += blockLength;
}

File.WriteAllBytes(@"output.mem", memory);
