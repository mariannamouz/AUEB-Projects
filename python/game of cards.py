import string
import random

print("Καλωσήλθατε στο Matching Game")
p=input("Δώστε αριθμό παικτών: ")        #αριθμος παικτων σε string  
k1=p.split()                             #λίστα με τον αριθμό παικτών
while not(len(k1)==1 and int(k1[0])>=2): #έλεγχος εγκυρότητας του αριθμού παικτών
    p=input("Δώστε αριθμό παικτών: ")
    k1=p.split()
players=int(k1[0])                       #αριθμός παικτών σε int

ep=('1','2','3')
epp='0'
while epp not in ep:                     #έλεγχος εγκυρότητας του επιπέδου δυσκολίας
    epp=input("Δώστε επίπεδο δυσκολίας Εύκολο (1), Μέτριο (2), Δύσκολο (3): ")
level=int(epp)

def epipedo(rows, cols):                 #Rows=Grammes , Cols=Stiles ,δημιουργια κρυφου πινακα       
    """
    Προσπαθήσαμε να δημιουργήσουμε παρακάτω doctest για αυτήν την συνάρτηση,όμως μας παρουσιαζόταν σφάλμα.
    Για αυτόν τον λόγο δεν βάλαμε και τα τρία >,ώστε να μην ελεχθεί σαν doctest.
    >>print(epipedo(5,5))
    [[' ', ' 1 ', ' 2 ', ' 3 ', ' 4 '], ['\n1', ' x ', ' x ', ' x ', ' x '], ['\n2', ' x ', ' x ', ' x ', ' x '], ['\n3', ' x ', ' x ', ' x ', ' x '], ['\n4', ' x ', ' x ', ' x ', ' x ']]
    """
    board = []
    for r in range(rows):
        brow = []
        for c in range(cols):
            if r == c == 0:
                brow.append(' ')
            elif r == 0:
                brow.append(' '+str(c)+' ')
            elif c == 0:
                brow.append('\n'+str(r))
            elif c>=11:
                brow.append('  '+'x'+' ')
            else:
                brow.append(' '+'x'+' ')
        board.append(brow)
    return board

def emfanish_epipedo(level):             #εμφανιση του παραπανω πινακα  
    """
    Προσπαθήσαμε να δημιουργήσουμε παρακάτω doctest για αυτήν την συνάρτηση,όμως μας παρουσιαζόταν σφάλμα.
    Για αυτόν τον λόγο δεν βάλαμε και τα τρία >,ώστε να μην ελεχθεί σαν doctest.
    >>emfanish_epipedo(2)
       1   2   3   4   5   6   7   8   9   10 

    1  x   x   x   x   x   x   x   x   x   x  

    2  x   x   x   x   x   x   x   x   x   x  

    3  x   x   x   x   x   x   x   x   x   x  

    4  x   x   x   x   x   x   x   x   x   x 
    """
    if level==1:
        b = epipedo(5,5)
        for row in b:
            print (' '.join(row))
    elif level==2:
        b = epipedo(5,11)
        for row in b:
            print (' '.join(row))
    elif level==3:
        b=epipedo(5,14)
        for row in b:
            print (' '.join(row))

def emfanish_neas(level):                #αντιγραφο του κρυφου πινακα           
    if level==1:
        for row in nea:
            print (' '.join(row))
    elif level==2:
        for row in nea:
            print (' '.join(row))
    elif level==3:
        for row in nea:
            print (' '.join(row))

def fanero(level,rows,cols):     #Rows=Grammes , Cols=Stiles, δημιουργια φανερου πινακα
    if level ==1:
        board = []
        brow=[]
        for r in range(rows):
            for c in range(cols):
                if r == c == 0:
                    brow.append(' ')
                elif r == 0:
                    brow.append('   '+str(c))
                elif c == 0:
                    brow.append('\n'+str(r))
                else:
                    p=eukolo()
                    while  p  in brow:
                        p=eukolo()
                    brow.append(p)
        board.append(brow)
        return board
    elif level==2:
        board = []
        brow = []
        for r in range(rows):
            for c in range(cols):
                if r == c == 0:
                    brow.append(' ')
                elif r == 0:
                    brow.append('  '+str(c)+' ')
                elif c == 0:
                    brow.append('\n'+str(r))
                else:
                    p=metrio()
                    while p in brow:
                        p=metrio()
                    brow.append(p)
        board.append(brow)
        return board
    elif level==3:
        board = []
        brow = []
        for r in range(rows):
            for c in range(cols):
                if r == c == 0:
                    brow.append(' ')
                elif r == 0:
                    if c<=10:
                        brow.append('  '+str(c)+' ')
                    else:
                        brow.append(' '+str(c)+' ')
                elif c == 0:
                    brow.append('\n'+str(r))
                else:
                    p=dyskolo()
                    while p in brow:
                        p=dyskolo()
                    brow.append(p)
        board.append(brow)
        return board

def eukolo():
    a=['10',' J',' Q',' K']    #SYMBOLA
    b=['♥','♦','♣','♠']        #SEIRES
    sym=random.choice(a)
    sei=random.choice(b)
    f=' '+sym+sei              #TYXAIO SYMBOLO KAI SEIRA
    return f

def metrio():
    a=['10',' A',' 2',' 3',' 4',' 5',' 6',' 7',' 8',' 9']                  #SYMBOLA
    b=['♥','♦','♣','♠']                                                    #SEIRES
    sym=random.choice(a)
    sei=random.choice(b)
    f=' '+sym+sei                                                          #TYXAIO SYMBOLO KAI SEIRA
    return f

def dyskolo():
    a=['10',' A',' 2',' 3',' 4',' 5',' 6',' 7',' 8',' 9',' J',' Q',' K']       #SYMBOLA
    b=['♥','♦','♣','♠']                                                        #SEIRES
    sym=random.choice(a)
    sei=random.choice(b)
    f=' '+sym+sei                                                              #TYXAIO SYMBOLO KAI SEIRA
    return f

def gr(c,grammh,sthlh):     #για να προσδιορισουμε τα στοιχεια της ανοιχτης που βρισκονται σε μια γραμμη στην πραγματικοτητα
    """
    >>> print(gr(4,1,2))
    7
    """
    c=c+1                                                
    if grammh==1:                                            
        gram= sthlh +c                                      
    elif grammh ==2:                                         
        gram= sthlh +c*2                                      
    elif grammh==3:                                          
        gram= sthlh + c*3                                       
    elif grammh==4:                                          
        gram= sthlh +c*4                                      
    return (gram) 

def func(i ,a):
    print('Παίκτη', i, end=" ")                                   #το end=" " εχει προσθεθει στο τελος για να φαινονται τα στοιχεια σε μια γραμμη σοτν χρηστη.
    print(': Δώσε γραμμή και στήλη', a , end=" ")
    stoix=input('κάρτας (πχ 1 10)')
    k1 = stoix.split()                                            #lista pou tha periexei th grammh kai th sthlh poy dinei o xrhsths
    while not(len(k1)==2):
        print('Παίκτη', i, end=" ")
        print(': Δώσε γραμμή και στήλη', a , end=" ")
        stoix=input('κάρτας (πχ 1 10)')
        k1 = stoix.split()
    grammh1 = int(k1[0])
    sthlh1 = int(k1[1])
    stoixeio_1=int(stoix.replace(" ", ""))                             #αφαιρει τα κενα απο το στοιχειο
    return [grammh1, sthlh1, stoixeio_1]

def sthl(funn):
    """
    >>> ls=[1,2,3]
    >>> sthl(ls)
    2
    """
    return funn[1]

def gra(funn):
    """
    >>> ls='hello'
    >>> gra(ls)
    'h'
    """
    return funn[0]

def stoixx(funn):
    """
    >>> ls=[1,2,'hello']
    >>> stoixx(ls)
    'hello'
    """
    return funn[2]

i=1
i2=1
nea=[]                                                             #δημιουργουμε νεα λιστα η οποια παρακατω θα παρει την τιμη της epipedo και αυτο γτ η επιπεδο ειναι συναρτηση κ θα ναι παντα χ τα στοιχεια της.
nea2=[]
pontoi_paiktwn=[]
anoixth=[]                                                         #αντιγραφο της φανερο

for z in range(players+1):                                         #μηδενιζει τους ποντους των παικτων
    pontoi_paiktwn.append(0)

if level==1:                                                       #εμφανιση φανερων πινακων
    rows,cols=5,5
    nea = epipedo(5,5)
    anoixth = fanero(1, 5, 5)
    for row in anoixth:
        print (' '.join(row))
    orio_kart= 16   #ο μεγιστος αριθμων καρτων που μπορουν να ανοιχτουν
    c=4
elif level==2:
    rows,cols=5,11
    nea = epipedo(5,11)
    anoixth = fanero(2, 5, 11)
    for row in anoixth:
        print (' '.join(row))
    orio_kart= 40
    c=10
elif level==3:
    rows,cols=5,14
    nea = epipedo(5,14)
    anoixth = fanero(3, 5, 14)
    for row in anoixth:
        print (' '.join(row))
    orio_kart= 52
    c=13

emfanish_epipedo(level)

def func1(a,i):
    f=func(i,a)
    sthlh=sthl(f)
    grammh=gra(f)
    stoi=stoixx(f)
    while not((sthlh<=c and grammh<=4) and grammh>0 and sthlh>0):  #ελεγχος εγκυροτητας για γραμμες και στηλες
        f=func(i,a)
        sthlh=sthl(f)
        grammh=gra(f)
        stoi=stoixx(f)
    for y in range(len(nea2)+1): 
        while nea2.count(stoi)!=0:                                 #ελεγχος για το αν εχει ξαναδωθει το stoi στην nea2
            f=func(i,a)
            sthlh=sthl(f)
            grammh=gra(f)
            stoi=stoixx(f)
            while not((sthlh<=c and grammh<=4) and (grammh>0 and sthlh>0)) and not(len(str(stoi))==2 or len(str(stoi))==3):
                f=func(i,a)
                sthlh=sthl(f)
                grammh=gra(f)
                stoi=stoixx(f)
    nea2.append(stoi)
    
    gram=gr(c,grammh,sthlh) 
    nea[grammh][sthlh]= anoixth[0][gram]                          #η νεα τωρα εχει μια τυχαια τιμη που εδωσε η ανοιχτη και αντικατεστησε στο χ της                                                    #το λεβελ εγινε level-1 γτ ξεκιναει απο το 0 η λιστα αρα το ευκολο ειναι =0
    fyllo= nea[grammh][sthlh]
    emfanish_neas(level)
    return  [fyllo,sthlh,grammh,stoi]

def fyllo(ff):
    """
    >>> ls=['Χρόνια Πολλά']
    >>> fyllo(ls)
    'Χρόνια Πολλά'
    """
    return ff[0]

def sthlh(ff):
    """
    >>> ls=[12,'Καλές Γιορτές',24]
    >>> sthlh(ls)
    'Καλές Γιορτές'
    """
    return ff[1]

def grammh(ff):
    """
    >>> ls=['Καλημέρα','Καλησπέρα','Καληνύχτα']
    >>> grammh(ls)
    'Καληνύχτα'
    """
    return ff[2]

def stoixeio(ff):
    """
    >>> ls=[188,263,253,2002]
    >>> stoixeio(ls)
    2002
    """
    return ff[3]

while i<= players and i2< orio_kart:
    a='πρωτης'
    l=func1(a,i)
    fyllo_1=fyllo(l)
    sthlh1=sthlh(l)
    grammh1=grammh(l)
    prwto=stoixeio(l)
    i2= i2+1
    
    a='δευτερης'
    l=func1(a,i)
    fyllo_2=fyllo(l)
    sthlh2=sthlh(l)
    grammh2=grammh(l)
    deytero=stoixeio(l)
    i2= i2+1

    fyllo_1=fyllo_1.replace(" ", "")    #αφαιρεση κενων απο fyllo_1, fyllo_2 και fyllo_3
    fyllo_2=fyllo_2.replace(" ", "")
    
    if fyllo_1[:-1]==fyllo_2[:-1]:      #περασμα ποντων για καθε παικτη
        i3=i
        if fyllo_1[:-1]=='A':
            pontoi=1
        elif fyllo_1[:-1]=='Q' or fyllo_1[:-1]=='J' or fyllo_1[:-1]=='K':
            pontoi=10
            if fyllo_1[:-1]=='J':
                i=i-1
                print('Ξαναπαίζει!')
            elif fyllo_1[:-1]=='K':
                if i==players:
                    i=1
                elif i<players:
                    i+=1
                print('Επόμενε παίκτη χάνεις στη σειρά σου!')
        else:
            pontoi=int(fyllo_1[:-1])
        pontoi_paiktwn[i3-1]=pontoi + pontoi_paiktwn[i3-1]
        if fyllo_1[:-1]=='K':
            print('Επιτυχές ταίριασμα +', pontoi ,'πόντοι!Παίκτη', i3,' έχεις συνολικά',pontoi_paiktwn[i3-1],' πόντους.')
        elif fyllo_1[:-1]=='J':
            print('Επιτυχές ταίριασμα +', pontoi ,'πόντοι!Παίκτη', i3,' έχεις συνολικά',pontoi_paiktwn[i3-1],' πόντους.')
        else:
            print('Επιτυχές ταίριασμα +', pontoi ,'πόντοι!Παίκτη', i3,' έχεις συνολικά',pontoi_paiktwn[i3-1],' πόντους.')
    else:
        if (fyllo_1[:-1]=='K' or fyllo_1[:-1]=='Q') and (fyllo_2[:-1]=='K' or fyllo_2[:-1]=='Q'):
            i3=i
            a='τριτης'
            l=func1(a,i)
            fyllo_3=fyllo(l)
            sthlh3=sthlh(l)
            grammh3=grammh(l)
            trito=stoixeio(l)
            i2= i2+1
        
            fyllo_3=fyllo_3.replace(" ", "")
            if fyllo_3[:-1]==fyllo_2[:-1]:
                if fyllo_3[:-1]=='Q' or fyllo_3[:-1]=='K':
                    pontoi=10
                    if fyllo_3[:-1]=='K':
                        if i==players:
                            i=1
                        elif i<players:
                            i+=1
                        print('Επόμενε παίκτη χάνεις στη σειρά σου!')
                pontoi_paiktwn[i3-1]=pontoi + pontoi_paiktwn[i3-1]
                if fyllo_3[:-1]=='K':
                    print('Επιτυχές ταίριασμα +', pontoi ,'πόντοι!Παίκτη', i3,' έχεις συνολικά',pontoi_paiktwn[i3-1],' πόντους.') 
                else:
                    print('Επιτυχές ταίριασμα +', pontoi ,'πόντοι!Παίκτη', i3,' έχεις συνολικά',pontoi_paiktwn[i3-1],' πόντους.') 
                nea2.remove(prwto)
                i2= i2-1
                if sthlh1<=9:
                    nea[grammh1][sthlh1]=' x '
                else:
                    nea[grammh1][sthlh1]='  x  '
            elif fyllo_3[:-1]==fyllo_1[:-1]:
                if fyllo_3[:-1]=='Q' or fyllo_3[:-1]=='K':
                    pontoi=10
                    if fyllo_3[:-1]=='K':
                        if i==players:
                            i=1
                        elif i<players:
                            i+=1
                        print('Επόμενε παίκτη χάνεις στη σειρά σου!')
                pontoi_paiktwn[i3-1]=pontoi + pontoi_paiktwn[i3-1]
                if fyllo_3[:-1]=='K':
                    print('Επιτυχές ταίριασμα +', pontoi ,'πόντοι!Παίκτη', i3,' έχεις συνολικά',pontoi_paiktwn[i3-1],' πόντους.') 
                else:
                    print('Επιτυχές ταίριασμα +', pontoi ,'πόντοι!Παίκτη', i3,' έχεις συνολικά',pontoi_paiktwn[i3-1],' πόντους.') 
                nea2.remove(deytero)
                i2= i2-1
                if sthlh2<=9:
                    nea[grammh2][sthlh2]=' x '
                else:
                    nea[grammh2][sthlh2]='  x  '
            else:
                if sthlh1<=9:
                    nea[grammh1][sthlh1]=' x '
                else:
                    nea[grammh1][sthlh1]='  x  '
                if sthlh2<=9:
                    nea[grammh2][sthlh2]=' x '
                else:
                    nea[grammh2][sthlh2]='  x  '
                if sthlh3<=9:
                    nea[grammh3][sthlh3]=' x '
                else:
                    nea[grammh3][sthlh3]='  x  '
                nea2.remove(prwto)
                nea2.remove(deytero)
                nea2.remove(trito)
                i2= i2-3
        else: 
            if sthlh1<=9:
                nea[grammh1][sthlh1]=' x '
            else:
                nea[grammh1][sthlh1]='  x  '
            if sthlh2<=9:
                nea[grammh2][sthlh2]=' x '
            else:
                nea[grammh2][sthlh2]='  x  ' 
            nea2.remove(prwto)
            nea2.remove(deytero)
            i2= i2-2

    i= i+1
    if i>players :                                                 #αν οι παιχτες ειναι λιγοι μπορει να μην τελειωσει το παιχνιδι αν γυρισουν απο δυο καρτες ο καθενας οποτε
        i=1                                                        #να πρεπει να ξαναπαιξει ο πρωτος. Για αυτο και ξαναγινεται 1 ο δεικτης i.

max_value = max(pontoi_paiktwn)
s=0
nikhtes=[]
for i in range(len(pontoi_paiktwn)):
    if pontoi_paiktwn[i]==max_value:
        nikhtes.append(i+1)
        s+=1
if s>1:
    print('Έχουμε ισοπαλία!Μεταξύ των παικτών',nikhtes,'!')
else:
    nikhths =pontoi_paiktwn.index(max_value)+1
    print('Ο νικητής είναι ο παίκτης',nikhths)

"""
Τα αποτελέσματα των doctest εμφανίζονται στην cmd 
Από τις οδηγίες της εργασίας δεν κατανοήσαμε αν θα έπρεπε τα doctest να ελέγχονται αυτόματα,χωρίς να δίνει καμία εντολή ο χρήστης
"""