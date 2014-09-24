#include <iostream>
#include "singleton.h"
using namespace std;

int main()
{
    cout << "Hello World!" << endl;
    Singleton* s1 = Singleton::instance();
    s1->outMsg();
    Singleton* s2 = Singleton::instance();
    s2->outMsg();
    return 0;
}

