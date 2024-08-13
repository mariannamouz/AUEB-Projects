#--------------------- Askisi 1 -----------------------

def location(name, lat, lon, type):
    """Kataskeuazei syn8eto dedomeno topo8esias (location).

    name -- onoma (str)
    lat -- gewfrafiko platos (se moires)
    lon -- gewgrafiko mikos (se moires)
    type -- eidos topo8esias (str)

    Epistrefei dedomeno pou anaparista tin topo8esia me onoma name h opoia
    brisketai sto gewgrafiko platos kai mikos lat kai lon antistoixa. To type
    einai string pou perigrafei to eidos tis topo8esias, p.x., 'monument',
    'bus station'.
    """
    return [name,float(lat),float(lon),type]


def name(loc):
    """Epistrefei to onoma mias topo8esias.

    loc -- topo8esia (typou location)

    Epistrefei to onoma (str) tis topo8esias loc.

    >>> monast = location('Monastiraki', 37.976362, 23.725947, 'square')
    >>> name(monast)
    'Monastiraki'
    """
    return loc[0]

def longitude(loc):
    """Gewgrafiko mikos.

    loc -- dedomeno location

    Epistrefei gewgrafiko mikos tis topo8esias loc

    >>> monast = location('Monastiraki', 37.976362, 23.725947, 'square')
    >>> longitude(monast)
    23.725947
    """
    return loc[2]

def lattitude(loc):
    """Gewgrafiko platos.

    loc -- dedomeno location

    Epistrefei gewgrafiko mikos tis topo8esias loc

    >>> monast = location('Monastiraki', 37.976362, 23.725947, 'square')
    >>> lattitude(monast)
    37.976362
    """
    return loc[1]


def type(loc):
    """Eidos topo8esias.

    loc -- dedomeno location

    Epistrefei string pou perigrafei to eidos tis topo8esias loc, p.x.,
    'monument', 'bus station'.

    >>> monast = location('Monastiraki', 37.976362, 23.725947, 'square')
    >>> type(monast)
    'square'
    """
    return loc[3]

#--------------------- Askisi 3 -----------------------

def pick_cherries_only():
    """Emfanizei string pou briskontai se fwliasmenes listes.

    Prepei na exei to akolou8o apotelesma:

    >>> pick_cherries_only()
    cherry1
    cherry2
    cherry3
    cherry4
    Yay!!!
    """
    """ SYMPLHRWSTE TA KENA APO KATW."""
    fruits = ['cherry1', 'orange', \
              ['grape', 'cherry2', ['cherry3'], 'banana'], \
              None, 'cherry4', [[['Yay!!!']]]]
    print(fruits[0])
    print(fruits[2][1])
    print(fruits[2][2][0])
    print(fruits[4])
    print(fruits[5][0][0][0])
        

#--------------------- Askisi 4 -----------------------

def pick_cherries_onebyone():
    """Emfanizei string pou briskontai se fwliasmenes listes.

    Prepei na exei to akolou8o apotelesma:

    >>> pick_cherries_onebyone()
    cherry1
    cherry2
    cherry3
    cherry4
    last cherry
    """
    """ SYMPLHRWSTE TA KENA APO KATW."""
    cherry_field = ['cherry1', ['cherry2', ['cherry3', ['cherry4', ['last cherry', None]]]]]

    print(cherry_field[0])
    cherry_field[0]=cherry_field[1][0]
    print(cherry_field[0])
    cherry_field[0]=cherry_field[1][1][0]
    print(cherry_field[0])
    cherry_field[0]=cherry_field[1][1][1][0]
    print(cherry_field[0])
    cherry_field[0]=cherry_field[1][1][1][1][0]
    print(cherry_field[0])


#--------------------- Askisi 5 -----------------------

def pick_cherries(field):
    """Emfanizei string pou briskontai se fwliasmenes listes.

    field -- lista me fwliasmena string. Ka8e lista exei dyo stoixeia: 
    to prwto einai string kai to deutero einai eite lista ths idias 
    morfhs 'h None. (Opws kai h cherry_field sto swma ths synarthshs 
    pick_cherries_onebyone()).

    Leitoyrgei opws i pick_cherries_onebyone, omws gia au8aireta polles
    fwliasmenes listes stin field.

    Paradeigmata:

    >>> cherry_field = ['cherry1', ['cherry2', ['cherry3', ['cherry4', ['last cherry', None]]]]]
    >>> pick_cherries(cherry_field)
    cherry1
    cherry2
    cherry3
    cherry4
    last cherry
    >>> pick_cherries(['Hello', ['world', None]])
    Hello
    world
    """
    """ SYMPLHRWSTE TA KENA APO KATW."""
    print(field[0])
    x=False
    while x==False:
        field=field[1]
        print(field[0])
        if field[1]==None:x=True


#--------------------- Askisi 6 -----------------------

def flatten(field):
    """Epistrefei lista afairwntas fwliasmenes listes.

    field -- lista me fwliasmena string. Ka8e lista exei dyo stoixeia: 
    to prwto einai string kai to deutero einai eite lista ths idias 
    morfhs 'h None. (Opws kai h cherry_field sto swma ths synarthshs 
    pick_cherries_onebyone()).

    Epistrefei nea lista pou periexei ola ta string pou briskontai sti
    field, xwris omws na periexontai se fwliasmenes listes.

    Paradeigmata:

    >>> cherry_field = ['cherry1', ['cherry2', ['cherry3', ['cherry4', ['last cherry', None]]]]]
    >>> flatten(cherry_field)
    ['cherry1', 'cherry2', 'cherry3', 'cherry4', 'last cherry']
    >>> flatten(['Hello', ['world', None]])
    ['Hello', 'world']
    >>> flatten(['Lone cherry', None])
    ['Lone cherry']
    """
    """GRAPSTE TON KWDIKA SAS APO KATW."""
    f=[]
    f+=[field[0]]
    x=False
    if field[1]==None:x=True
    while x==False:
        field=field[1]
        f+=[field[0]]
        if field[1]==None:x=True
    print(f)



#--------------------- Askisi 7 -----------------------

def cherry_string(field):
    """String me ola ta string pou periexoun 'cherry' sti field.

    field -- lista me fwliasmena string. Ka8e lista exei dyo stoixeia: 
    to prwto einai string kai to deutero einai eite lista ths idias 
    morfhs 'h None. 
    (Opws kai h cherry_field sto swma ths synarthshs pick_cherries_onebyone()).

    Epistrefei string pou exei proel8ei apo synenwsh olwn twn string
    pou periexontai sti field kai periexoun th le3h 'cherry'.

    Paradeigmata:

    >>> cherry_field = ['cherry1', ['cherry2', ['cherry3', ['cherry4', ['last cherry', None]]]]]
    >>> cherry_string(cherry_field)
    'cherry1cherry2cherry3cherry4last cherry'
    >>> cherry_string(['Hello', ['cherry', None]])
    'cherry'
    >>> cherry_string(['Hello', ['first cherry', ['world', ['last cherry', None]]]])
    'first cherrylast cherry'
    """
    """ SYMPLHRWSTE TA KENA APO KATW."""
    from functools import reduce
    from operator import add
    return reduce(add,[x for x in flatten(field) if 'cherry' in x])
#--------------------- Askisi 8 -----------------------

def unflatten(ls):
    """Epistrefei stoixeia topo8etwntas ta se fwliasmenes listes.

    ls -- lista pou ta stoixeia tis einai string.

    Epistrefei lista me fwliasmena string, opws to orisma ths synarthshs 
    cherry string. Mia tetoia lista exei dyo stoixeia: to prwto einai
    string kai to deutero einai eite lista ths idias morfhs 'h None.

    Paradeigmata:

    >>> unflatten(['Hello', 'world'])
    ['Hello', ['world', None]]
    >>> unflatten(['Hello'])
    ['Hello', None]
    >>> unflatten(['No', 'more', 'cherries', 'please!'])
    ['No', ['more', ['cherries', ['please!', None]]]]
    """
    """GRAPSTE TON KWDIKA SAS APO KATW."""
    i=2
    f=[ls[len(ls)-1],None]
    while i<=len(ls):
        f=[ls[len(ls)-i],f]
        i+=1
    return f