#include "VMValue.h"
#include "VMObject.h"
#include "logger/log.h"

namespace apryx {

	VMValue::VMValue()
		: m_Raw(), m_Type(Type::VOID)
	{
	}

	VMValue::VMValue(const VMValue &other)
	{
		m_Type = other.m_Type;

		m_Raw.l1 = 0;
		m_Raw.l2 = 0;

		if (m_Type == Type::OBJECT) {
			m_VMObject = other.m_VMObject;
		}
		else {
			m_Raw = other.m_Raw;
		}
	}

	VMValue::VMValue(VMValue &&other)
		: m_Raw(std::move(other.m_Raw)), m_Type(std::move(other.m_Type))
	{

	}

	VMValue::~VMValue()
	{

	}

	VMValue &VMValue::operator=(const VMValue &other)
	{
		m_Type = other.m_Type;
		m_Raw.l1 = 0;
		m_Raw.l2 = 0;

		if (m_Type == Type::OBJECT) {
			m_VMObject = other.m_VMObject;
		}
		else {
			m_Raw = other.m_Raw;
		}

		return *this;
	}
	std::ostream & operator<<(std::ostream & o, const VMValue & slot)
	{
		switch (slot.getType()) {
		case  apryx::VMValue::Type::FLOAT:
			o << slot.m_Float;
			break;
		case apryx::VMValue::Type::INT:
			o << slot.m_Int;
			break;
		case apryx::VMValue::Type::NATIVE_FUNCTION:
			o << "function_native(" << slot.m_Native << ")";
			break;
		case apryx::VMValue::Type::OBJECT:
			o << *slot.m_VMObject;
			break;
		default:
			o << "void[possible_incorrect_order](" << std::hex << slot.m_Raw.l1 << slot.m_Raw.l2 << std::dec << ")";
			break;
		}

		return o;
	}
}
