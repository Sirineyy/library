#include "voiture.h"

Voiture::Voiture(){
memset(immatriculation,NULL,10);
memset(couleur,NULL,20);
}

void Voiture::intialise (char *imat,char *color,float p){
strcpy(immatriculation,imat);
strcpy(couleur,color);
poids=p;
}
void Voiture::affiche(void){
message("Caracterestiques de la voiture ");
cout<<endl<<"Immatriculation:"<<immatriculation<<endl;
cout<<"couleur:"<<couleur<<endl;
cout<<"poids"<<poids<<endl;
}
void Voiture::message(char *msg){
cout<<msg<<endl;
}

