#include <iostream>
#include "factory.h"
#include "product.h"

using namespace std;

int main()
{
    cout << "Hello World!" << endl;
    AbstractFactory* factory = new ConcreteFactory;
    AbstractProduct* product = factory->createProduct();
    return 0;
}

