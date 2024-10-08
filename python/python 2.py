"""Seira Askhsewn 2."""


def sum_sequence(n, term):
    """A8roisma arxikwn orwn akolou8ias

    n -- deikths teleutaiou orou (deikths prwtou = 1)
    term -- synarthsh: term(i) einai o i-ostos oros

    Epistrefei tin timi term(1) + term(2) + ... + term(n).
    """
    i, sum = 2, term(1)
    while i <= n:
        sum += term(i)
        i += 1
    return sum


#>>>>>>>>>>>>>>>>>>>>> Askhsh 1 <<<<<<<<<<<<<<<

def paei_pi(n):
    """A8roisma n orwn tis akoloy8ias 
              4, -4/3, 4/5, -4/7, 4/9, -4/11, 4/13, ... ktl.

    n -- 8etikos akeraios >= 1

    Epistrefei to a8roisma twn orwn 1 ews kai n.

    Paradeigmata:
    >>> paei_pi(1)
    4.0
    >>> paei_pi(2)
    2.666666666666667
    >>> paei_pi(3)
    3.466666666666667
    >>> paei_pi(1000)
    3.140592653839794
    >>> paei_pi(10000)
    3.1414926535900345
    """
    def term(n):
        return 4/(n*2-1) if ((n*2-1-n) % 2==0) else 4/(n*2-1)*(-1)
    return sum_sequence(n, term)


#>>>>>>>>>>>>>>>>>>>>> Askhsh 2 <<<<<<<<<<<<<<<

def stars_horizontal(n):
    """String asteriwn topo8etimena orizontiws.

    n -- akeraios >= 1
    
    Epistrefei string me n xaraktires '*'.

    Paradeigmata:
    >>> stars_horizontal(1)
    '*'
    >>> stars_horizontal(5)
    '*****'
    >>> print(stars_horizontal(5))
    *****
    """
    def func(i):
        return '*' # mporeite na xrhsimopoihsete perissoteres 'h ligoteres grammes
    return sum_sequence(n , func)


#>>>>>>>>>>>>>>>>>>>>> Askhsh 3 <<<<<<<<<<<<<<<

def stars_vertical(n):
    """String asteriwn topo8etimena ka8etws.

    n -- akeraios >= 1

    Epistrefei string me n xaraktires '*' me ena '*' se
    ka8e grammi. Sto telos tou string den yparxei o 
    xaraktiras allagis grammis '\n'.

    Paradeigmata:
    >>> stars_vertical(1)
    '*'
    >>> print(stars_vertical(2))
    *
    *
    >>> print(stars_vertical(5))
    *
    *
    *
    *
    *
    """
    """ GRAPSTE TON KWDIKA SAS APO KATW """
    def func1(i):# mporeite na xrhsimopoihsete perissoteres 'h ligoteres grammes
        if n==i:
            return '*'
        else:
            return '*\n'
    return sum_sequence(n , func1)


#>>>>>>>>>>>>>>>>>>>>> Askhsh 4 <<<<<<<<<<<<<<<

def make_quadratic(a, b, c):
    """Polywnymo 2ou ba8mou.

    a, b, c -- ari8moi

    Epistrefei thn tetragwnikh synarthsh f(x) = a*x*x+b*x+c.

    Paradeigmata:
    >>> f = make_quadratic(1, 2, 1.0)
    >>> f(0)
    1.0
    >>> f(-1)
    0.0
    >>> f(1)
    4.0
    >>> f(1.5)
    6.25
    """
    return lambda x: a*x*x+b*x+c


#>>>>>>>>>>>>>>>>>>>>> Askhsh 5 <<<<<<<<<<<<<<<

def abacize(op):
    """Ekdoxh abaka (ari8mhthri) enos telesti.

    op -- synartisi dyo akeraiwn orismatwn
          pou epistrefei akeraia timi

    Epistrefetai nea synartisi pou dexetai ta
    ari8mitika orismata tis op kai epistrefei ta apotelesma tis
    xrhsimopoiwntas anaparastasi me xandres ari8mitiriou.

    Paradeigmata:
    >>> from operator import *
    >>> baby_add = abacize(add)
    >>> baby_add('o', 'o')
    'oo'
    >>> baby_add('oo', 'ooo')
    'ooooo'
    >>> baby_mul = abacize(mul)
    >>> baby_mul('oo', 'ooo')
    'oooooo'
    >>> abacize(max)('oo', 'ooo')
    'ooo'
    >>> abacize(pow)('oo', 'ooo')
    'oooooooo'
    """
    """ GRAPSTE TON KWDIKA SAS APO KATW """
    def g(x,y):
        x=len(x)
        y=len(y)
        return (str(op(x,y)*('o')))
    return g



#>>>>>>>>>>>>>>>>>>>>> Askhsh 6 <<<<<<<<<<<<<<<

def sum_squares(n):
    """A8roisma tetragwnwn.

    n -- 8etikos akeraios >= 1

    Epistrefei tin timi 1 + 2*2 + 3*3 + ... + n*n.

    Paradeigmata:
    >>> sum_squares(1)
    1
    >>> sum_squares(2)
    5
    >>> sum_squares(100)
    338350
    """
    """PREPEI NA LEITOYRGEI ANADROMIKA, XWRIS ENTOLES
    EPANALHPSHS OPWS while, for.
    """
    if n==1:
        return 1
    else:
        return n*n + sum_squares(n-1)


#>>>>>>>>>>>>>>>>>>>>> Askhsh 7 <<<<<<<<<<<<<<<

def star_area(n):
    """Ypologizei ton ari8mo twn '*' se dentra.

    n -- 8etikos akeraios >= 1

    Epitrefei to plh8os twn '*' pou emfanizontai sta dentra
    xaraktirwn '*' mege8ous n. Ta dentra auta stin prwti grammi
    periexoun 1 '*', 2 '*' sti 2h grammi,..., n '*' stin teleutaia.
    Px., dentro mege8ous 1:
    *
    mege8ous 2:
    *
    **
    mege8ous 3:
    *
    **
    ***

    Paradeigmata:
    >>> star_area(1)
    1
    >>> star_area(2)
    3
    >>> star_area(3)
    6
    >>> star_area(5)
    15
    >>> star_area(100)
    5050
    """
    """ GRAPSTE TON KWDIKA SAS APO KATW """
    """PREPEI NA LEITOYRGEI ANADROMIKA, XWRIS ENTOLES
    EPANALHPSHS OPWS while, for.
    """
    p=0
    if n==1:
        return 1
    else:
        return n+star_area(n-1)

#>>>>>>>>>>>>>>>>>>>>> Askhsh 8 <<<<<<<<<<<<<<<

def print_digits(x):
    """Emfanizei ta pshfia ari8mou.

    x -- 8etikos akeraios >= 0

    Emfanizei ena ena ta pshfia tou x arxizontas 
    apo to pio simantiko pshfio.

    Paradeigmata:
    >>> print_digits(0)
    0
    >>> print_digits(2019)
    2
    0
    1
    9
    >>> print_digits(923884)
    9
    2
    3
    8
    8
    4
    """
    """ GRAPSTE TON KWDIKA SAS APO KATW """
    """PREPEI NA LEITOYRGEI ANADROMIKA, XWRIS ENTOLES
    EPANALHPSHS OPWS while, for.
    """
    if x==0:
        print (0)
    else:
        a=x%10
        x=x//10
        if x>0:
            print_digits(x)
        print (a)

#>>>>>>>>>>>>>>>>>>>>> Askhsh 9 <<<<<<<<<<<<<<<

def askisi9():
    """Ypologismoi gewmetrikwn sximatwn.

    >>> askisi9()
    Perifereies kyklwn me aktina 1 ews 10
    6.283185307179586
    12.566370614359172
    18.84955592153876
    25.132741228718345
    31.41592653589793
    37.69911184307752
    43.982297150257104
    50.26548245743669
    56.548667764616276
    62.83185307179586
    Emvada tetragwnwn me pleura 1 ews 8
    1
    4
    9
    16
    25
    36
    49
    64
    Emvado kyklou aktinas 1 = 3.141592653589793
    Emvado kyklou aktinas 2 = 12.566370614359172
    Emvado kyklou aktinas 3 = 28.274333882308138
    Emvado kyklou aktinas 4 = 50.26548245743669
    Emvado kyklou aktinas 5 = 78.53981633974483
    """
    """ GRAPSTE TON KWDIKA SAS APO KATW, APOFEYGONTAS
    TIN EPANALIPSI PAROMOIWN ENTOLWN kai XRHSIMOPOIWNTAS
    OSO TO DYNATON LIGOTERES GRAMMES KWDIKA """
    from math import pi
    def circle_periphery(r):
        if r>0:
            circle_periphery(r-1)
            print (2*pi*r)
    def square_area(x):
        if x>0:
            square_area(x-1)
            print (x*x)
    print ('Perifereies kyklwn me aktina 1 ews 10')
    circle_periphery(10)
    print ('Emvada tetragwnwn me pleura 1 ews 8')
    square_area(8)
    def circle_area(r):
        if r>1:
            circle_area(r-1)
        print ('Emvado kyklou aktinas',r,'=',pi*(r**2))
    circle_area(5)
