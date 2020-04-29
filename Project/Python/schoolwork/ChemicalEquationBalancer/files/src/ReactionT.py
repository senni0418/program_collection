## @file ReactionT.py
#  @author Senni Tan
#  @brief Module that implements a type of ReactionT
#  @date Feb 8, 2020

from ChemTypes import *
from CompoundT import *
from sympy import *
from sympy.solvers.solveset import linsolve


## @brief A local function that determines if every element in a
#  set is positive
#  @param s the given set that needs to be checked
#  @return True if all elements are positive, false otherwise
def pos(s):
    for i in s:
        if i <= 0:
            return False
    return True


## @brief A local function that determines the number of atom 'e'
#  in a given chemical reaction
#  @param C the given sequence of compounds which represents
#  a chemical reaction
#  @param c the given set of coefficients
#  @param e the given element that needs to be checked
#  @return The number of atoms of element 'e' in the reaction
def n_atoms(C, c, e):
    length = len(C)
    num = 0
    for i in range(length):
        num += c[i] * C[i].num_atoms(e)
    return num


## @brief A local function that determines the elements in the
#  chemical equation
#  @param C	the given sequence of compounds which represents
#  a chemical reactiona
#  @return The set of elements in the chemical equation
def elm_in_chem_eq(C):
    s = []
    for c in C:
        a = c.constit_elems().s
        for i in a:
            s.append(i)
    return s


## @brief A local function that determines if the given element
#  is balanced in the given chemical equation with 	the given
#  coefficients
#  @param L sequence of CompoundT that represents the compounds of
#  left side of the chemical equation
#  @param R sequence of Compound that represents the compounds of
#  right side of the chemical equation
#  @param cL sequence of number that represents the coefficients
#  of the left side of the chemical equation
#  @param cR sequence of number that represents the coefficients
#  of the left side of the chemical equation
#  @param e the given element
#  @return True if the given elements is balanced in the given
#  chemical equaiton, false otherwise
def is_bal_elm(L, R, cL, cR, e):
    if n_atoms(L, cL, e) == n_atoms(R, cR, e):
        return True
    return False


## @brief A local function that determines if the given chemical
#  equation is balanced with the given compounds and coefficients
#  @param L sequence of CompoundT that represents the compounds of
#  left side of the chemical equation
#  @param R sequence of Compound that represents the compounds of
#  right side of the chemical equation
#  @param cL sequence of number that represents the coefficients
#  of the left side of the chemical equation
#  @param cR sequence of number that represents the coefficients
#  of the left side of the chemical equation
#  @return True if the given chemical equation is balanced, false
#  otherwise
def is_balanced(L, R, cL, cR):
    if not elm_in_chem_eq(L) == elm_in_chem_eq(R):
        return False
    for e in elm_in_chem_eq(L):
        if not is_bal_elm(L, R, cL, cR, e):
            return False
    return True


## @brief A local function that turns the result into a list
#  @details The linsolve function of the sympy library is used
#  in this program to solve a matrix. The default result will be a
#  tuple and the result needs to be turned in a list
#  @param r the tuple of the result
#  @return The list of the result
def result_into_list(r):
    r = list(r)
    r = list(r[0])
    return r


## @brief A local function that turns the result into a list of
#  number
#  @details The linsolve function of the sympy library is used
#  in this program to solve a matrix. The default result will be a
#  relation between symbols and the result needs to be turned into
#  numbers
#  @param result the list of the result
#  @return The list of the result turned into numbers
def result_into_coeff(result, sym):
    r = []
    for i in result:
        i = i.subs(sym, 1)
        r.append(i)
    return r


## @brief A local function that determine the greatest common factor
#  of two numbers
#  @param a one of the two given numbers
#  @param b one of the two given numbers
#  @return the greatest common factor of two given numbers
def gcd(a, b):
    while b > 0:
        a, b = b, a % b
    return a


## @brief A local function that determine the greatest common factor
#  of a list of numbers
#  @param a the given list of numbers
#  @return the greatest common factor of all numbers in the list
def gcd_of_list(a):
    result = a[0]
    for i in a[1:]:
        result = gcd(result, i)
    return result


## @brief A local function that determine the factorial of 100
#  @The number of factorial of 100
def factorial_100():
    n = 100
    r = 1
    while n > 0:
        r *= n
        n -= 1
    return r


## @brief A local function that turns a list of numbers that
#  represents the coefficients by fractions into representing
#  by integers
#  @param r the given list of numbers
#  @return The list of numbers that represents the coefficients by
#  integer
def frac_into_int(r):
    fac100 = factorial_100()
    result = []
    for i in r:
        num = i * fac100
        result.append(num)
    divisor = gcd_of_list(result)
    new = []
    for i in result:
        num = i // divisor
        new.append(num)
    return new


## @brief An abstract data type that represents a chemical reaction
class ReactionT():

    ## @brief The constructor of the ReactionT
    #  @param l the sequence of compoundT that represents the compounds
    #  on the left side of the chemical equation
    #  @param r the sequence of compoundT that represents the compounds
    #  on the righr side of the chemical equation
    def __init__(self, l, r):
        self.coeffL = []
        self.coeffR = []
        if not elm_in_chem_eq(l) == elm_in_chem_eq(r):
            raise ValueError
        elements = elm_in_chem_eq(l)
        # construct symbols
        tau0 = symbols('tau0')

        # construct matrix of left side elements
        left_matrix = []
        for e in elements:
            elm_matrix_row = []
            for compound in l:
                a = compound.num_atoms(e)
                elm_matrix_row.append(a)
            left_matrix.append(elm_matrix_row)
        left_len = len(left_matrix[0])
        # construct matrix of right side elements
        right_matrix = []
        for e in elements:
            elm_matrix_row = []
            for compound in r:
                a = compound.num_atoms(e)
                elm_matrix_row.append(a)
            right_matrix.append(elm_matrix_row)
        # right matrix times -1
        for row in right_matrix:
            for i in range(len(row)):
                row[i] *= -1
        # join the right matrix and left matrix
        matrix = []
        for i in range(len(elements)):
            combined_row = left_matrix[i] + right_matrix[i]
            matrix.append(combined_row)
        # put an zero at the end of each row
        for i in range(len(elements)):
            matrix[i].append(0)
        # solve for matrix
        result = linsolve(Matrix(matrix))
        # turn result in to a list
        result = result_into_list(result)
        result = result_into_coeff(result, tau0)
        # get rid of fraction
        result = frac_into_int(result)
        # put result in to left coefficient and right coefficient
        for i in range(left_len):
            self.coeffL.append(result[i])
        slice_r = result[left_len:]
        for i in slice_r:
            self.coeffR.append(i)
        # check value error for coefficients
        if not pos(self.coeffL):
            raise ValueError
        if not pos(self.coeffR):
            raise ValueError
        if not is_balanced(l, r, self.coeffL, self.coeffR):
            raise ValueError

        self.lhs = l.copy()
        self.rhs = r.copy()

    ## @brief The getter method of class ReactionT
    #  @return The list of compounds on the left side of the equation
    def get_lhs(self):
        return self.lhs

    ## @brief The getter method of class ReactionT
    #  @return The list of compounds on the rignt side of the equation
    def get_rhs(self):
        return self.rhs

    ## @brief The getter method of class ReactionT
    #  @return The list of coefficients on the left side of the equation
    def get_lhs_coeff(self):
        return self.coeffL

    ## @brief The getter method of class ReactionT
    #  @return The list of coefficients on the right side of the
    #  equation
    def get_rhs_coeff(self):
        return self.coeffR
