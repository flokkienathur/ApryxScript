#pragma once

#include <vector>

#include "VMTypes.h"

namespace apryx {

	class ScriptVM {
		VMStack m_Stack;

		std::vector<instruction_t> m_Instructions;

	public:
		ScriptVM(std::vector<instruction_t>);

		void execute();

		//Extra usefull stuff
	public:
		inline void push(const VMOperandSlot &s)
		{
			m_Stack.back().m_OperandStack.push_back(s);
		}
		inline void push(VMOperandSlot &&s)
		{
			m_Stack.back().m_OperandStack.push_back(s);
		}
		inline VMOperandSlot pop()
		{
			VMOperandSlot slot;

			slot = m_Stack.back().m_OperandStack.back();
			m_Stack.back().m_OperandStack.pop_back();

			return std::move(slot);
		}
		inline void vpop()
		{
			m_Stack.back().m_OperandStack.pop_back();
		}
		inline VMOperandSlot &top()
		{
			return m_Stack.back().m_OperandStack.back();
		}

		friend class VMResources;
	};
}