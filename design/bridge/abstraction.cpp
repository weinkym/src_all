#include "abstraction.h"
#include "abstractionimp.h"

Abstraction::~Abstraction()
{
}

Abstraction::Abstraction()
{
}


RefinedAbstraction::RefinedAbstraction(AbstractionImp *imp)
    :Abstraction()
    ,m_imp(imp)
{
}

RefinedAbstraction::~RefinedAbstraction()
{
}

void RefinedAbstraction::operation()
{
    m_imp->operation();
}
