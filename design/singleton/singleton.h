#ifndef SINGLETON_H
#define SINGLETON_H
#include <cstdio>

class Singleton
{
public:
    ~Singleton();
    static Singleton* instance();
    void outMsg();

protected:
    Singleton();

private:
    static Singleton* m_instance;
    int m_count;
};

#endif // SINGLETON_H
