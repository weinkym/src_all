#include "factory.h"
#include "product.h"
#include <stdio.h>

ConcreteFactory::ConcreteFactory()
{
    printf("ConcreteFactory\n");
}

ConcreteFactory::~ConcreteFactory()
{
}

AbstractProduct *ConcreteFactory::createProduct()
{
    return new ConcreteProduct();
}


AbstractFactory::~AbstractFactory()
{
}

AbstractFactory::AbstractFactory()
{
}
