#include "singleton.h"

Singleton* Singleton::m_instance = NULL;

Singleton::~Singleton()
{
}


Singleton::Singleton()
    :m_count(0)
{
}


Singleton *Singleton::instance()
{
    if(m_instance == NULL)
    {
        m_instance = new Singleton();
    }
    return m_instance;
}


void Singleton::outMsg()
{
    printf("Singleton::outMsg() count = %d\n",m_count++);
}
