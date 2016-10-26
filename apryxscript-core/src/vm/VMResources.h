#pragma once

#include "VMTypes.h"
#include <vector>

namespace apryx {

	struct VMSlot;
	class VMResources {
		//TODO inlining
	public:
		static void writeInstruction(std::vector<instruction_t> &target, instruction_t instruction);
		static void writeIndex(std::vector<instruction_t> &target, index_t instruction);
		static void writeFloat(std::vector<instruction_t> &target, float_t f);
		static void writeInt(std::vector<instruction_t> &target, int_t i);
		static void writeShort(std::vector<instruction_t> &target, short_t s);
		static void writeByte(std::vector<instruction_t> &target, byte_t b);

		static instruction_t readInstruction(std::vector<instruction_t> &target, index_t &pc);
		static index_t readIndex(std::vector<instruction_t> &target, index_t &pc);
		static float_t readFloat(std::vector<instruction_t> &target, index_t &pc);
		static int_t readInt(std::vector<instruction_t> &target, index_t &pc);
		static short_t readShort(std::vector<instruction_t> &target, index_t &pc);
		static byte_t readByte(std::vector<instruction_t> &target, index_t &pc);
	};
}