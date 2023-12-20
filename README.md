# Sklepik

Aplikacja ma umozliwiać zarządzanie sklepikiem. Polega ona na mozliwości dodawania zakupionych produktów, doliczania marży itp.
Przewiduje podział na 3 zakładki:
- zakupy
- produkty
- statystyki

Z kolegą prowadzimy arkusz gdzie wpisujemy zakupy, ilość produktów, cena zakupu:

```
Kto,Co,Ilość,Cena,Razem,Czy rozliczono,Cena sprzedaży,suma sprzedaży,Zysk,,,,,,,,,,,
Daniel ,Nożyczki,1,"10,00 zł","10,00 zł",Rozliczone,"0,00 zł","0,00 zł","-10,00 zł",,,,,,,,,,,
Kamil,cola 330ml,3,"2,20 zł","6,60 zł",Rozliczone,"3,00 zł","9,00 zł","2,40 zł",,,,,,,,,,,
Kamil,tiger 250ml,6,"2,00 zł","12,00 zł",Rozliczone,"3,00 zł","18,00 zł","6,00 zł",,,,,,,,,,,
Kamil,twix xtra,6,"3,00 zł","18,00 zł",Rozliczone,"4,00 zł","24,00 zł","6,00 zł",,,,,,,,,,,
Kamil,cola 500ml,5,"3,00 zł","15,00 zł",Rozliczone,"4,50 zł","22,50 zł","7,50 zł",,,,,,,,,,,
Kamil,black 500ml,11,"3,70 zł","40,70 zł",Rozliczone,"4,50 zł","49,50 zł","8,80 zł",,,,,,,,,,,
Kamil,oshee,14,"2,50 zł","35,00 zł",Rozliczone,"3,50 zł","49,00 zł","14,00 zł",,,,,,,,,,,
Kamil,princessa,10,"0,90 zł","9,00 zł",Rozliczone,"2,00 zł","20,00 zł","11,00 zł",,,,,,,,,,,
Kamil,knoppers,9,"1,20 zł","10,80 zł",Rozliczone,"2,00 zł","18,00 zł","7,20 zł",,,,,,,,,,,
Kamil,black energetyk,12,"3,60 zł","43,20 zł",Rozliczone,"4,50 zł","54,00 zł","10,80 zł",,,,,,,,,,,
Daniel ,Price Polo,5,"1,67 zł","8,35 zł",,"2,00 zł","10,00 zł","1,65 zł",,,,,,,,,,,
Daniel ,Snickers,3,"2,99 zł","8,97 zł",,"4,00 zł","12,00 zł","3,03 zł",,,,,,,,,,,
Daniel ,lion baton,8,"1,49 zł","11,92 zł",,"3,00 zł","24,00 zł","12,08 zł",,,,,,,,,,,
Daniel ,Kitkat,8,"1,64 zł","13,12 zł",,"3,00 zł","24,00 zł","10,88 zł",,,,,,,,,,,
Kamil,snickers duży,6,"2,55 zł","15,30 zł",Rozliczone,"4,00 zł","24,00 zł","8,70 zł",,,,,,,,,,,
Kamil,twix xtra,15,"2,40 zł","36,00 zł",Rozliczone,"4,00 zł","60,00 zł","24,00 zł",,,,,,,,,,,
Kamil,tiger 250ml,2,"1,50 zł","3,00 zł",Rozliczone,"3,00 zł","6,00 zł","3,00 zł",,,,,,,,,,,
Kamil,black 250ml,6,"2,00 zł","12,00 zł",Rozliczone,"3,00 zł","18,00 zł","6,00 zł",,,,,,,,,,,
Kamil,bounty duże,9,"2,70 zł","24,30 zł",Rozliczone,"4,00 zł","36,00 zł","11,70 zł",,,,,,,,,,,
Daniel ,Prince Polo Mleczne,35,"1,29 zł","45,15 zł",,"2,00 zł","70,00 zł","24,85 zł",,,,,,,,,,,
Daniel ,Kinder Bueno,16,"2,79 zł","44,64 zł",,"3,00 zł","48,00 zł","3,36 zł",,,,,,,,,,,
Kamil,prince polo różne,30,"1,30 zł","39,00 zł",Rozliczone,"2,00 zł","60,00 zł","21,00 zł",,,,,,,,,,,
Kamil,7days,9,"1,90 zł","17,10 zł",Rozliczone,"3,00 zł","27,00 zł","9,90 zł",,,,,,,,,,,
Kamil,grzesiek standard,17,"1,00 zł","17,00 zł",,"1,50 zł","25,50 zł","8,50 zł",,,,,,,,,,,
Kamil,black,8,"2,00 zł","16,00 zł",,"3,00 zł","24,00 zł","8,00 zł",,,,,,,,,,,
Kamil,tiger,6,"3,50 zł","21,00 zł",,"4,50 zł","27,00 zł","6,00 zł",,,,,,,,,,,
Kamil,światełka ,1,"40,00 zł","40,00 zł",,"0,00 zł","0,00 zł","-40,00 zł",,,,,,,,,,,
Daniel ,Kinder Bueno,30,"2,29 zł","68,70 zł",,"3,00 zł","90,00 zł","21,30 zł",,,,,,,,,,,
Daniel ,Kinder Bueno White,32,"2,29 zł","73,28 zł",,"3,00 zł","96,00 zł","22,72 zł",,,,,,,,,,,
Kamil,lotki do rzutek,1,"13,00 zł","13,00 zł",,"0,00 zł","0,00 zł","-13,00 zł",,,,,,,,,,,
Kamil,zegar czasowy do lampek,1,"18,00 zł","18,00 zł",,"0,00 zł","0,00 zł","-18,00 zł",,,,,,,,,,,
Kamil,kinder bueno,20,"2,30 zł","46,00 zł",,"3,00 zł","60,00 zł","14,00 zł",,,,,,,,,,,
Kamil,zastawa wigilia,1,"59,00 zł",,,,,,,,,,,,,,,,
```

Na zakładce zakupy masz miec listę zakupów i przycisk dodaj zakup. Otwiera Ci sie dialog i uzupełniasz formularz (kto kupił, za ile, ile sztuk, co, jaka cena sprzedaży)
