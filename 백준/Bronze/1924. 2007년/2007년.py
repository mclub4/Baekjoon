days = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"]
dates = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

s = input().split()
m = int(s[0])
d = int(s[1])
date_sum = 0

for i in range(m - 1):
    date_sum += dates[i]
date_sum += d

for i in range(7):
    if date_sum % 7 == i:
        print(days[i])