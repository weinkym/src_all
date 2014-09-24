#ifndef ABSTRACTION_H
#define ABSTRACTION_H
#include <cstdio>

class AbstractionImp;
class Abstraction
{
public:
    ~Abstraction();
    virtual void operation() = 0;

protected:
    Abstraction();
};

class RefinedAbstraction : public Abstraction
{
public:
    RefinedAbstraction(AbstractionImp* imp);
    ~RefinedAbstraction();

    void operation();

private:
    AbstractionImp* m_imp;
};

#endif // ABSTRACTION_H
