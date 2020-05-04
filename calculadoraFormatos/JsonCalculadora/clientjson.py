import operator
import json
import socket		 	 
import sys
import ipaddress

if __name__ == '__main__':
    s = socket.socket()		#criando socket
    host = '192.168.1.8'      	#fornecendo endereço IP	
    port = 3000                           # forencendo número da porta
    s.connect((host, port))                           # Conectando com o servidor
    print("Endereço IP do servidor:", host)
    print("Porta do servidor:", port)
    stack = []					#inciando lista vazia
    while True:					#laço para manter a calculadora ativa até que o usuário resolva sair
        expression = input('Escreva o cálculo em notação polonesa reversa (Ex: 5 3 8 / -). Use "q" para sair > ')	#input para receber a expressão notação polonesa reversa
        if len(expression)==0:		#condição para caso nada ser escrito o input se manter
            continue
        stack = expression.split()	#divide cada elemento da expressão em um item da lista vazia criada anteriormente
        stackJson = json.dumps(stack)	#converte a lista em uma string json
        s.send(stackJson.encode())	#envia a string json em forma de bytes para o servidor
        result = s.recv(1024).decode()	#espera receber a resposta vinda do servidor e armazena o resultado
        if result == "Quit":		#condição para caso o cliente escolheu terminar de realizar cálculos
            print("Conexão Fechada")	
            break
        print("Resultado:", result)	#imprime o resultado
    s.close				#fecha a conexão
