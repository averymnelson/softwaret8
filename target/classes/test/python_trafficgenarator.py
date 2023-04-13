import socket
import random
import time

bufferSize  = 1024
serverAddressPort   = ("127.0.0.1", 7501)


print('this program will generate some test traffic for 2 players on the purple ')
print('team as well as 2 players on the pink team')
print('')

purpleteamsize = int(input('Enter number of purple team players ==> '))
pinkteamsize = int(input('Enter number of pink team players ==> '))

purpleteam = [i for i in range(1, purpleteamsize + 1)]
pinkteam = [i for i in range(1, pinkteamsize + 1)]

i = 1
while i <= purpleteamsize:
    purpleteam[i-1] = input('Enter id of purple player ' + str(i) + ' ==> ')
    i+=1

i = 1
while i <= pinkteamsize:
    pinkteam[i-1] = input('Enter id of pink player ' + str(i) + ' ==> ')
    i+=1

print('')
counter = input('How many events do you want ==> ')

# Create datagram socket
UDPClientSocketTransmit = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

# counter number of events, random player and order
i = 1
while i < int(counter):
	if random.randint(1,2) == 1:
		message = purpleteam[random.randint(0, purpleteamsize - 1)] + " hit " + pinkteam[random.randint(0, pinkteamsize - 1)]
	else:
		message = pinkteam[random.randint(0, pinkteamsize - 1)] + " hit " + purpleteam[random.randint(0, purpleteamsize - 1)]

	print(message)
	i+=1
	UDPClientSocketTransmit.sendto(str.encode(str(message)), serverAddressPort)
	time.sleep(random.randint(1,3))

UDPClientSocketTransmit.sendto(str.encode(str("bye")), serverAddressPort)
print("bye")