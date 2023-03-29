a = int(input())
b = a//5
if a%5 != 0 :
 for i in range(b+1) : 
  if (a-5*(b-i))%3 == 0 :
      print((b-i) + (a-5*(b-i))//3)
      break
 else : print(-1)
else : print(b)