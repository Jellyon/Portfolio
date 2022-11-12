"""named functions for testing"""
def combine(x:int, y:int) -> int:
    return x*y
def comp(x:float, y:float) -> float:
    if x>y:
        return x
    else:
        return y
def twofour(x:float) -> bool:
    if x<2.0 or x>4.0:
        return False
    else:
        return True


def Map(F, l:list) -> list:
    """Made for paired tuples for the assignment, won't work on regular list without a try/except block"""
    n =[]
    copy = l[:]
    if not copy.__len__()==0:
        while copy.__len__()>0:
            n.append(F(copy[0][0], copy[0][1]))
            copy.pop(0)
    return n
class Emptylist(Exception):
   """Raises an Exception Emptylist"""
   pass
def Reduce(F, l:list) -> float:
    """Explicitly designed to output a float for the assignment"""
    copy = l[:]
    if copy.__len__()==0:
        raise Emptylist
    elif copy.__len__()==1:
        return copy[0]
    else:
        while copy.__len__()>1:
            copy[0] = F(copy[0],copy[1])
            copy.pop(1)
        return copy[0]

def Filter(F, l:list)-> list:
    """should filter any list with given criteria of the function F"""
    n =[]
    copy = l[:]
    if copy.__len__()==0:
        return []
    else:
        while copy.__len__()>0:
            if F(copy[0]):
                n.append(copy[0])
            copy.pop(0)
        return n

empty_list = []
pair_list=[(1,7), (2,8), (3,2)]
float_list = [5.0, 1.9, 3.9, 3.1, 2.0, 4.0, 4.2, 7.5]

# print("Testing Map with named function:")
# new_pair_list = pairsMap(combine, pair_list)
# print("result:\t",str(new_pair_list))
print("Testing Map with anonymous function (x*y) of [(1,7), (2,8), (3,2)]")
new_pair_list = Map(lambda x,y : x*y, pair_list)
print("result:\t",str(new_pair_list))


# print("Testing Reduce with named function:")
# minimum_float = floatReduce(comp, float_list)
# print("result:\t",str(minimum_float))
print("Testing Reduce with anonymous function  to find minimum of [5.0, 1.9, 3.9, 3.1, 2.0, 4.0, 4.2, 7.5]")
minimum_float = Reduce(lambda x,y : y if(x>y) else x , float_list)
print("result:\t",str(minimum_float))

# print("Testing Filter with named function:")
# new_float_list = listFilter(twofour, float_list)
# print("result:\t",str(new_float_list))
print("Testing Filter with anonymous function to find vals bewteen 2.0 and 4.0 of [5.0, 1.9, 3.9, 3.1, 2.0, 4.0, 4.2, 7.5]")
new_float_list = Filter(lambda x : False if(x<2.0 or x>4.0) else True, float_list)
print("result:\t",str(new_float_list))
