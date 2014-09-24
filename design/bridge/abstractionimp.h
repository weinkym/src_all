#ifndef ABSTRACTIONIMP_H
#define ABSTRACTIONIMP_H
#include <cstdio>

class AbstractionImp
{
public:
    ~AbstractionImp();
    virtual void operation() = 0;

protected:
    AbstractionImp();
};

class ImpA : public AbstractionImp
{
public:
    ImpA();
    ~ImpA();
    void operation();
};

class ImpB : public AbstractionImp
{
public:
    ImpB();
    ~ImpB();
    void operation();
};

#endif // ABSTRACTIONIMP_H
