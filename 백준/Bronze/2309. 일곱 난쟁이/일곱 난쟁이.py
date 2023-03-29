a = []
total = 0
for i in range(9):
    b = int(input())
    a.append(b)
total = sum(a)
for i in range(9):
  for j in range(i+1,9):
      if a[i]+a[j] == (total-100) :
          a.pop(i)
          a.pop(j-1)
          a.sort()
          for k in range(7):
              print(a[k])
          exit()
