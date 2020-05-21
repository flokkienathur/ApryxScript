#pragma once

#include <vector>
#include <string>

#include "Type.h"
#include "Field.h"
#include "Function.h"

namespace apryx {
	
	class Class {
		std::string m_Name;
		std::vector<Field> m_Fields;
		std::vector<FunctionStatement> m_Functions;
	public:
		Class(std::string name, std::vector<Field> fields, std::vector<FunctionStatement> functions);

		int getFunctionIndexBySignature(const std::string &name);
		FunctionStatement &getFunction(int index);
		
		int getFieldIndex(const std::string &name);
		Field &getField(int index);

		const std::string &name() const { return m_Name; }
	};

}