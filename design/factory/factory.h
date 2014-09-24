#ifndef FACTORY_H
#define FACTORY_H

class AbstractProduct;
class AbstractFactory
{
public:
    virtual ~AbstractFactory() = 0;
    virtual AbstractProduct* createProduct() = 0;

protected:
        AbstractFactory();
};

class ConcreteFactory : public AbstractFactory
{
public:
    ConcreteFactory();
    ~ConcreteFactory();
    AbstractProduct* createProduct();
};

#endif // FACTORY_H
