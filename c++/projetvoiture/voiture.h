#ifndef voiture_H
#define voiture_H
using namespace std;
#include <cstring>
#include <iostream>
class Voiture{
public:
    Voiture();
    void intialise (char *, char *,float );
    void affiche (void);
    void affiche (void);
private :
    char immatriculation[10];
    char couleur[20];
    float poids;
    void message(char *);

    };
#endif
