import socket
import random
import time

bufferSize  = 1024
serverAddressPort   = ("127.0.0.1", 7501)


print('this program will generate some test traffic for 2 players on the red ')
print('team as well as 2 players on the green team')
print('')

redteamsize = int(input('Enter number of red team players ==> '))
greenteamsize = int(input('Enter number of green team players ==> '))

redteam = [i for i in range(1, redteamsize + 1)]
greenteam = [i for i in range(1, greenteamsize + 1)]

i = 1
while i <= redteamsize:
    redteam[i-1] = input('Enter id of red player ' + str(i) + ' ==> ')
    i+=1

i = 1
while i <= greenteamsize:
    greenteam[i-1] = input('Enter id of green player ' + str(i) + ' ==> ')
    i+=1

print('')
counter = input('How many events do you want ==> ')

# Create datagram socket
UDPClientSocketTransmit = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

# counter number of events, random player and order
i = 1
while i < int(counter):
	if random.randint(1,2) == 1:
		message = redteam[random.randint(0, redteamsize - 1)] + " hit " + greenteam[random.randint(1, greenteamsize - 1)]
	else:
		message = greenteam[random.randint(1, greenteamsize - 1)] + " hit " + redteam[random.randint(0, redteamsize - 1)]

	print(message)
	i+=1
	UDPClientSocketTransmit.sendto(str.encode(str(message)), serverAddressPort)
	time.sleep(random.randint(1,3))

print("bye")