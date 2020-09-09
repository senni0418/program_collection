## @file Set.py
#  @author Senni Tan
#  @brief Module that creates a generic set
#  @date Feb 6, 2020

from Equality import *


## @brief An generic abstract data type that represents a set
class Set(Equality):

    ## @brief Set constructor
    #  @details Put the elements of the input set or sequence into a
    #  new Set
    #  @param ss a sequence or a set of elements of some type
    def __init__(self, ss):
        self.s = []
        for i in ss:
            self.s.append(i)

    ## @brief A function that adds an element into the Set
    #  @param e an input element
    def add(self, e):
        self.s.append(e)

    ## @brief A function that removes an element from the Set
    #  @details Raise ValueError if the element is not in the Set
    #  @param e an input element
    def rm(self, e):
        if e not in self.s:
            raise ValueError
        self.s.remove(e)

    ## @brief A function that checks if the given element is in the
    #  Set
    #  @param e an input element
    #  @return True if the given element is in the Set, false otherwise
    def member(self, e):
        for i in self.s:
            if e == i:
                return True
        return False

    ## @brief A function that determines how many elements in the Set
    #  @return The number of elements in the Set
    def size(self):
        num = 0
        for i in self.s:
            num += 1
        return num

    ## @brief A function that determines if the current Set object is
    #  equal to the given Set object
    #  @param R the given Set
    #  @return True if two Sets are in the same size and contains the
    #  same elements, false otherwise
    def equals(self, R):
        if not self.size() == R.size():
            return False
        for i in self.s:
            if not R.member(i):
                return False
        return True

    ## @brief A function that turns the current Set into a sequence
    #  return A set that is turned from Set
    def to_seq(self):
        return self.s


'''
s = Set([1,2,3,4])
print(s.s)
s.rm(4)
print(s.s)
s.add(5)
print(s.s)
print(s.member(1))
print(s.member(6))
n = s.size()
print(n)
s2 = Set([1,2,3,4])
s2.add(5)
s2.rm(4)
print(s2.s)
s3 = Set([1,2,3,5])
print(s.equals(s2))
print(s.equals(s3))
s = Set([1,2,3,4])
print(s.s)
s = s.to_seq()
print(s)
s3.add(6)

'''
