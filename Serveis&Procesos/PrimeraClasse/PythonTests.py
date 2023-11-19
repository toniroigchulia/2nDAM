# i = 1
# while i <= 10:
#     print("\n","Tabla del ", i ,"\n")
#     j = 1
#     while j <= 10:
    
#         print(i*j)
        
#         j = j + 1

#     i = i + 1
    
matrx1 = [[2, 7],[9,0]]
matrx2 = [[6, 9],[5, 25]]


matrxresult = [[0,0],[0,0]]


for x in range (len(matrx1)):
    
    for y in range(len(matrx2)):
        
        matrxresult[x][y] = matrx1[x][y] + matrx2[x][y]
        
        
for h in matrxresult:

    print(h)