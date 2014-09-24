#ifndef PRODUCT_H
#define PRODUCT_H

class AbstractProduct
{
public:
    virtual ~AbstractProduct() = 0;

protected:
        AbstractProduct();

};

class ConcreteProduct : public AbstractProduct
{
public:
    ConcreteProduct();
    ~ConcreteProduct();
};

#endif // PRODUCT_H
