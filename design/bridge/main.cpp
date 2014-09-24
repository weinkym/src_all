#include <iostream>
#include "abstraction.h"
#include "abstractionimp.h"

using namespace std;

int main()
{
    cout << "Hello World!" << endl;
    AbstractionImp* impA = new ImpA();
    Abstraction * actionA = new RefinedAbstraction(impA);
    actionA->operation();

    AbstractionImp* impB = new ImpB();
    Abstraction * actionB = new RefinedAbstraction(impB);
    actionB->operation();

    return 0;
}

