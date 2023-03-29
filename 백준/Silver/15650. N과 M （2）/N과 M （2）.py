import itertools

n,m = map(int,input().split())

result = list(itertools.combinations(range(1,n+1),m))

for i in result:
    print(*i)