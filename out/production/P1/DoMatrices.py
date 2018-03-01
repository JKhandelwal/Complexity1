import scipy

from scipy.sparse import *
import numpy


def make_matrix(a):
    b = scipy.sparse.rand(a,a);
    print(b)


def make_row_column(a,b):
    ret_val = []

    if b == "columnIndex":
        for iterate in a:
            new_list=[]
            for num, list_value in enumerate(iterate, start=1):
                if list_value is not 0:
                    new_list.append((num,list_value))
            ret_val.append(new_list)
    else:
        for i in range(0,len(a)):
            ret_val.append([((j+1),item[i]) for j,item in enumerate(a) if item[i] is not 0])
        # print(ret_val)

    return ret_val


def multiply_matrices(a,b,dim):
    ret_val = []
    for a_num,a_value in enumerate(a):
        for b_numm, b_value in enumerate(b):
            if b_value or a_value = []:
                pass
            else:
                



if __name__ == "__main__":
    dim = 3
    m = [[1,0,3],[0,0,0],[0,0,0]]
    # print(m)
    a = make_row_column(m,"ow")
    print("the numberCount is ")
    print(a)
    print("\n")
    b = make_row_column(m,"columnIndex")
    print("the columnIndex is ")
    print(b)
    print("\n")

    ret = multiply_matrices(b,a,3)
    print(ret)
